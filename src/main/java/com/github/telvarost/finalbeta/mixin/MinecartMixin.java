package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.Minecart;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.block.Rail;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;

@Mixin(Minecart.class)
public class MinecartMixin {

	private static final double EXTRA_MINECART_XZ_SIZE = 0.4;
	private static final double EXTRA_MINECART_Y_SIZE = 0.0;

	@Inject(method = "getBoundingBox", at = @At("HEAD"), cancellable = true)
	public void finalBeta_onCollision(EntityBase other, CallbackInfoReturnable<Box> ci) {
		if (Config.ConfigFields.FIX_MINECART_STOPPING_ON_ITEMS) {
			if (other instanceof Item || other instanceof Arrow) {
				ci.setReturnValue(null);
			}
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void finalBeta_tick(CallbackInfo ci) {
		Minecart minecart = (Minecart) (Object) this;
		int x = MathHelper.floor(minecart.x);
		int y = MathHelper.floor(minecart.y);
		int z = MathHelper.floor(minecart.z);
		int tileId = minecart.level.getTileId(x, y, z);
		if (Rail.isRail(tileId)) {
			float speed = MathHelper.sqrt(minecart.velocityX * minecart.velocityX + minecart.velocityZ * minecart.velocityZ);
			float volume = 0;
			float pitch = 0;
			if (speed >= 0.01D) {
				if (minecart.passenger != null && Config.ConfigFields.FIX_MINECART_FLICKERING) {
					minecart.boundingBox.method_99(minecart.boundingBox.minX - EXTRA_MINECART_XZ_SIZE, minecart.boundingBox.minY,
							minecart.boundingBox.minZ - EXTRA_MINECART_XZ_SIZE, minecart.boundingBox.maxX + EXTRA_MINECART_XZ_SIZE,
							minecart.boundingBox.maxY + EXTRA_MINECART_Y_SIZE, minecart.boundingBox.maxZ + EXTRA_MINECART_XZ_SIZE);
				}
				++minecart.field_1645;
				pitch = ModHelper.clamp(pitch + 0.0025F, 0.0F, 1.0F);
				volume = ModHelper.lerp(ModHelper.clamp(speed, 0.0F, 0.5F), 0.0F, 0.7F);
			} else {
				volume = 0.0f;
				pitch = 0.0f;
			}

			if (speed >= 0.01D && Config.ConfigFields.ADD_MINECART_SOUNDS) {
				if (minecart.field_1645 % 33 == 1) {
					minecart.level.playSound(x, y, z, "minecart.base", volume, pitch);
				}
			}
		}
	}
}
