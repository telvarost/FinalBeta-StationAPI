package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.resource.language.TranslationStorage;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(VideoSettingsScreen.class)
public class VideoSettingsScreenMixin extends ScreenMixin {
	
	@Inject(method = "init", at = @At("TAIL"))
	public void init(CallbackInfo ci) {
		VideoSettingsScreen screen = (VideoSettingsScreen)(Object)this;
		this.buttons.add(new Button(300, screen.width / 2 - 155, screen.height / 6 + 96, 150, 20, this.getCloudsLabel()));
	}
	
	@Inject(method = "buttonClicked", at = @At("HEAD"))
	public void buttonClicked(Button btn, CallbackInfo ci) {
		if(btn.active) {
			if(btn.id == 300) {
				ModConfig.ENABLE_CLOUDS.set(!ModConfig.ENABLE_CLOUDS.get());
			}
		}
	}
	
	private String getCloudsLabel() {
		TranslationStorage i18n = TranslationStorage.getInstance();
		return "Clouds: " + (ModConfig.ENABLE_CLOUDS.get() ? i18n.translate("options.on") : i18n.translate("options.off"));
	}
}
