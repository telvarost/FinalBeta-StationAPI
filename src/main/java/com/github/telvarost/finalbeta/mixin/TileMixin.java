package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockBase;

@Mixin(BlockBase.class)
public class TileMixin {

	@Inject(method = "getTextureForSide(I)I", at = @At("HEAD"), cancellable = true)
	public void finalBeta_getTextureForSide(int i, CallbackInfoReturnable<Integer> ci) {
		BlockBase tile = (BlockBase) (Object) this;
		if (tile.id == BlockBase.GRASS.id) {
			if (i == 1) {
				ci.setReturnValue(0);
			} else if (i == 0) {
				ci.setReturnValue(2);
			} else {
				ci.setReturnValue(tile.texture);
			}
		}
	}
}
