package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.entity.FishHook;
import net.minecraft.entity.Item;

@Mixin(FishHook.class)
public class FishHookMixin {

	@ModifyArgs(method = "method_956", at = @At(value = "INVOKE", target = "Lnet/minecraft/level/Level;spawnEntity(Lnet/minecraft/entity/EntityBase;)Z"))
	private void finalBeta_onFishCaught(Args args) {
		if(Config.ConfigFields.FIX_FISHING) {
			Item item = args.get(0);
			FishHook hook = (FishHook) (Object) this;
			double x = hook.field_1067.x - hook.x;
			double y = hook.field_1067.y - hook.y;
			double z = hook.field_1067.z - hook.z;
			item.velocityX = x * 0.1D;
			item.velocityY = y * 0.1D + Math.sqrt(Math.sqrt(x * x + y * y + z * z)) * 0.05D;
			item.velocityZ = z * 0.1D;
		}
	}
}
