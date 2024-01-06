package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	@Shadow
	protected EntityModel field_909;
	
	@Shadow
	protected EntityModel model;
	
	@Inject(method = "method_821", at = @At("HEAD"), cancellable = true)
	public void method_821(LivingEntity entity, double d, double d1, double d2, CallbackInfo ci) {
		if (ModConfig.DISABLE_ID_TAGS.get()) {
			ci.cancel();
		}
	}
}
