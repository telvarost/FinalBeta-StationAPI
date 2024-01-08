package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import net.minecraft.item.ItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemBase;

@Mixin(TileEntityFurnace.class)
public class FurnaceEntityMixin {

	@Shadow
	private ItemInstance[] inventory;
	
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntityFurnace;getFuelTime(Lnet/minecraft/item/ItemInstance;)I", shift = At.Shift.BY, by = 5), cancellable = true)
	public void tick(CallbackInfo ci) {
		if(Config.ConfigFields.FIX_FURNACE_LAVA_BUCKET) {
			if(this.inventory[1] != null && this.inventory[1].itemId == ItemBase.lavaBucket.id) {
				ci.cancel();
				this.inventory[1] = new ItemInstance(ItemBase.bucket);
			}			
		}
	}
}
