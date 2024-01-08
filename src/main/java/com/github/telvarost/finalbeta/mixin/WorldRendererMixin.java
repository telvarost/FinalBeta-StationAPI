package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.WorldRenderer;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

	@Inject(method = "method_1552", at = @At("HEAD"), cancellable = true)
	public void cloudRenderer(float f, CallbackInfo ci) {
		if(!ModHelper.ModHelperFields.ENABLE_CLOUDS) {
			ci.cancel();
		}
	}

}
