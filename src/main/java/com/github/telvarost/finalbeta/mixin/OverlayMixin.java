package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.InGame;
import net.minecraft.client.render.TextRenderer;

@Mixin(InGame.class)
public class OverlayMixin extends DrawableHelper {

	@Shadow
	private Minecraft minecraft;
	
//	@Inject(
//		method = "renderHud",
//		at = @At(
//			value = "INVOKE",
//			target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V"
//		),
//		slice = @Slice(
//			from = @At(
//				value = "INVOKE",
//				target = "Lnet/minecraft/client/render/TextRenderer;drawTextWithShadow(Ljava/lang/String;III)V"
//			),
//			to = @At(
//				value = "FIELD",
//				target = "Lnet/minecraft/client/gui/InGame;jukeboxMessageTime:I",
//				opcode = Opcodes.GETFIELD
//			)
//		)
//	)
//	public void finalBeta_render(float f, boolean flag, int i, int j, CallbackInfo ci) {
//		TextRenderer textRenderer = this.minecraft.textRenderer;
//		this.drawTextWithShadow(textRenderer, "Days Played: " + ModHelper.getGameDaysPlayed() + " (" + ModHelper.getRealDaysPlayed() + ")", 2, 96, 14737632);
//	}
}
