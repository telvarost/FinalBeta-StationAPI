package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.gui.screen.ingame.Death;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Death.class)
public class DeathScreenMixin extends ScreenBase {

    public DeathScreenMixin() {
    }

	@Redirect(
			method = "render",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screen/ingame/Death;drawTextWithShadowCentred(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V",
					ordinal = 1
			)
	)
	private void finalBeta_getResourcesUrl(Death instance, TextRenderer textRenderer, String s, int i, int j, int k) {
		this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7e" + "0", i, j, k);
	}
}