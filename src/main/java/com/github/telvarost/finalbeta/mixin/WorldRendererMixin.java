package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.WorldRenderer;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

	@Inject(method = "method_1552", at = @At("HEAD"), cancellable = true)
	public void cloudRenderer(float f, CallbackInfo ci) {
		if(!ModConfig.ENABLE_CLOUDS.get()) {
			ci.cancel();
		}
	}
	
}
