package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.tileentity.TileEntityBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.item.ItemInstance;

@Mixin(TileEntityFurnace.class)
public abstract class FurnaceEntityMixin extends TileEntityBase implements InventoryBase {

	@Shadow
	private ItemInstance[] inventory;

	@Shadow public int burnTime;

	@Inject(
			method = "tick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/tileentity/TileEntityFurnace;getFuelTime(Lnet/minecraft/item/ItemInstance;)I"
			),
			cancellable = true
	)
	public void finalBeta_tickConsumeLavaBucket(CallbackInfo ci) {
		if(Config.ConfigFields.FIX_FURNACE_LAVA_BUCKET) {
			if(this.inventory[1] != null && this.inventory[1].itemId == ItemBase.lavaBucket.id) {
				ModHelper.ModHelperFields.IS_LAVA_BUCKET_CONSUMED = true;
			}			
		}
	}

	@Inject(method = "tick", at = @At(value = "RETURN"))
	public void finalBeta_tickConsumeLavaBucketReturnEmptyBucket(CallbackInfo ci) {
		if(ModHelper.ModHelperFields.IS_LAVA_BUCKET_CONSUMED) {
			this.inventory[1] = new ItemInstance(ItemBase.bucket);
			ModHelper.ModHelperFields.IS_LAVA_BUCKET_CONSUMED = false;
		}
	}
}
