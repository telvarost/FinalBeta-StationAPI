package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tile.StairsTile;
import net.minecraft.tile.Tile;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(StairsTile.class)
public class StairsTileMixin {

	@Inject(method = "beforeDestroyedByExplosion", at = @At("HEAD"), cancellable = true)
	public void beforeDestroyedByExplosion(Level arg, int i, int j, int k, int i1, float f, CallbackInfo ci) {
		if(ModConfig.FIX_STAIRS_DROPS.get()) {
			Tile tile = ((Tile) (Object) this);
			ItemInstance item = new ItemInstance(tile.id, 1, 0);
			if (!arg.isClient) {
				float var6 = 0.7F;
				double var7 = arg.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
				double var9 = arg.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
				double var11 = arg.rand.nextFloat() * var6 + (1.0F - var6) * 0.5D;
				ItemEntity var13 = new ItemEntity(arg, i + var7, j + var9, k + var11, item);
				var13.pickupDelay = 10;
				arg.spawnEntity(var13);
			}
			ci.cancel();		
		}
	}
}
