package xyz.pixelatedw.finalbeta.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.Overlay;
import net.minecraft.client.render.TextRenderer;
import xyz.pixelatedw.finalbeta.WyHelper;

@Mixin(Overlay.class)
public class OverlayMixin extends DrawableHelper {

	@Shadow
	private Minecraft minecraft;
	
	@Inject(
		method = "render(FZII)V", 
		at = @At(
			value = "INVOKE", 
			target = "Lorg/lwjgl/opengl/GL11;glPopMatrix()V"
		),
		slice = @Slice(
			from = @At(
				value = "INVOKE", 
				target = "drawTextWithShadow(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V"
			), 
			to = @At(
				value = "FIELD", 
				target = "jukeboxMessageTime:I",
				opcode = Opcodes.GETFIELD
			)
		)
	)
	public void render(float f, boolean flag, int i, int j, CallbackInfo ci) {
		TextRenderer textRenderer = this.minecraft.textRenderer;
		this.drawTextWithShadow(textRenderer, "Days Played: " + WyHelper.getGameDaysPlayed() + " (" + WyHelper.getRealDaysPlayed() + ")", 2, 96, 14737632);
	}
}
