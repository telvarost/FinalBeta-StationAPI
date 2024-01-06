package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.FurnaceEntity;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(FurnaceEntity.class)
public class FurnaceEntityMixin {

	@Shadow
	private ItemInstance[] contents;
	
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/FurnaceEntity;getFuelTime(Lnet/minecraft/item/ItemInstance;)I", shift = At.Shift.BY, by = 5), cancellable = true)
	public void tick(CallbackInfo ci) {
		if(ModConfig.FIX_FURNACE_LAVA_BUCKET.get()) {
			if(this.contents[1] != null && this.contents[1].itemId == ItemType.bucketLava.id) {
				ci.cancel();
				this.contents[1] = new ItemInstance(ItemType.bucket);
			}			
		}
	}
}
