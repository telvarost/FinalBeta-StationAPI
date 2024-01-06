package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.model.BipedModel;
import net.minecraft.client.render.entity.model.SkeletonModel;
import net.minecraft.client.render.entity.model.ZombieModel;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(ZombieModel.class)
public class SkeletonModelMixin {
	@Inject(method = "setAngles", at = @At("TAIL"))
	public void setAngles(float f, float f1, float f2, float f3, float f4, float f5, CallbackInfo ci) {
		if(ModConfig.FIX_BOW_MODEL.get()) {
			BipedModel model = ((BipedModel) (Object) this);
			if(model instanceof SkeletonModel) {
				model.leftArm.yaw = 0.45f;
				model.rightArm.yaw = -0.2f;
				model.leftArm.pitch -= 0.05f;
				model.rightArm.pitch -= 0.05f;
				model.rightArm.roll += 0.1f;			
			}			
		}
	}
}
