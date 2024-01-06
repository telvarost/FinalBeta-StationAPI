package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.animal.Pig;
import net.minecraft.item.ItemType;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(method = "dropLoot", at = @At("TAIL"))
	public void dropLoot(CallbackInfo ci) {
		if(ModConfig.FIX_SADDLES_NOT_DROPPING.get()) {
			LivingEntity entity = (LivingEntity) (Object) this;
			if (entity instanceof Pig && ((Pig) entity).isSaddled()) {
				entity.dropItem(ItemType.saddle.id, 1);
			}			
		}
	}
}
