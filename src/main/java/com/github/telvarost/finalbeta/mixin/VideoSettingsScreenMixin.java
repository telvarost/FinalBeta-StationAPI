package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.menu.VideoSettings;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.resource.language.TranslationStorage;

@Mixin(VideoSettings.class)
public class VideoSettingsScreenMixin extends ScreenMixin {
	
	@Inject(method = "init", at = @At("TAIL"))
	public void init(CallbackInfo ci) {
		VideoSettings screen = (VideoSettings)(Object)this;
		this.buttons.add(new Button(300, screen.width / 2 - 155, screen.height / 6 + 96, 150, 20, this.getCloudsLabel()));
	}
	
	@Inject(method = "buttonClicked", at = @At("HEAD"))
	public void buttonClicked(Button btn, CallbackInfo ci) {
		if(btn.active) {
			if(btn.id == 300) {
				ModData.ModDataFields.ENABLE_CLOUDS = !ModData.ModDataFields.ENABLE_CLOUDS;
			}
		}
	}
	
	private String getCloudsLabel() {
		TranslationStorage i18n = TranslationStorage.getInstance();
		return "Clouds: " + (ModData.ModDataFields.ENABLE_CLOUDS ? i18n.translate("options.on") : i18n.translate("options.off"));
	}
}
