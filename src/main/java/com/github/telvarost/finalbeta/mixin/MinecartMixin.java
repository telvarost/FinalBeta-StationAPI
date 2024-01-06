package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Minecart;
import net.minecraft.entity.projectile.Arrow;
import net.minecraft.tile.RailTile;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathsHelper;
import xyz.pixelatedw.finalbeta.ModConfig;
import xyz.pixelatedw.finalbeta.WyHelper;

@Mixin(Minecart.class)
public class MinecartMixin {

	private static final double EXTRA_MINECART_XZ_SIZE = 0.4;
	private static final double EXTRA_MINECART_Y_SIZE = 0.0;

	@Inject(method = "method_1379", at = @At("HEAD"), cancellable = true)
	public void onCollision(Entity other, CallbackInfoReturnable<Box> ci) {
		if (ModConfig.FIX_MINECART_STOPPING_ON_ITEMS.get()) {
			if (other instanceof ItemEntity || other instanceof Arrow) {
				ci.setReturnValue(null);
			}
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void tick(CallbackInfo ci) {
		Minecart minecart = (Minecart) (Object) this;
		int x = MathsHelper.floor(minecart.x);
		int y = MathsHelper.floor(minecart.y);
		int z = MathsHelper.floor(minecart.z);
		int tileId = minecart.level.getTileId(x, y, z);
		if (RailTile.method_1107(tileId)) {
			float speed = MathsHelper.sqrt(minecart.velocityX * minecart.velocityX + minecart.velocityZ * minecart.velocityZ);
			float volume = 0;
			float pitch = 0;
			if (speed >= 0.01D) {
				if (minecart.passenger != null && ModConfig.FIX_MINECART_FLICKERING.get()) {
					minecart.boundingBox.set(minecart.boundingBox.minX - EXTRA_MINECART_XZ_SIZE, minecart.boundingBox.minY,
							minecart.boundingBox.minZ - EXTRA_MINECART_XZ_SIZE, minecart.boundingBox.maxX + EXTRA_MINECART_XZ_SIZE,
							minecart.boundingBox.maxY + EXTRA_MINECART_Y_SIZE, minecart.boundingBox.maxZ + EXTRA_MINECART_XZ_SIZE);
				}
				++minecart.field_1645;
				pitch = WyHelper.clamp(pitch + 0.0025F, 0.0F, 1.0F);
				volume = WyHelper.lerp(WyHelper.clamp(speed, 0.0F, 0.5F), 0.0F, 0.7F);
			} else {
				volume = 0.0f;
				pitch = 0.0f;
			}

			if (speed >= 0.01D && ModConfig.ADD_MORE_SOUNDS.get()) {
				if (minecart.field_1645 % 33 == 1) {
					minecart.level.playSound(x, y, z, "minecart.base", volume, pitch);
				}
			}
		}
	}
}
