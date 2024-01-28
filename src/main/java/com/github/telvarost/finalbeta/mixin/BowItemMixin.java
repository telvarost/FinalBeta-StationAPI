package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.item.ItemBase;
import net.minecraft.item.tool.Bow;

@Mixin(Bow.class)
public class BowItemMixin {
	@Inject(method = "<init>(I)V", at = @At("TAIL"))
	public void finalBeta_initBow(int i, CallbackInfo ci) {
		if(Config.GraphicsConfig.FIX_BOW_MODEL) {
			ItemBase item = ((ItemBase) (Object) this);
			item.setRendered3d();
		}
	}
}
