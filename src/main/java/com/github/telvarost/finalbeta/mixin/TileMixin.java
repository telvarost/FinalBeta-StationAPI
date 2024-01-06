package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.tile.Tile;

@Mixin(Tile.class)
public class TileMixin {

	@Inject(method = "getTextureForSide(I)I", at = @At("HEAD"), cancellable = true)
	public void getTextureForSide(int i, CallbackInfoReturnable<Integer> ci) {
		Tile tile = (Tile) (Object) this;
		if (tile.id == Tile.GRASS.id) {
			if (i == 1) {
				ci.setReturnValue(0);
			} else if (i == 0) {
				ci.setReturnValue(2);
			} else {
				ci.setReturnValue(tile.tex);
			}
		}
	}
}
