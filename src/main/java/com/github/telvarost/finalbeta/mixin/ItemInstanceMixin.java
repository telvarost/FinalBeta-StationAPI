package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.EntityBase;
import net.minecraft.item.ItemInstance;

@Mixin(ItemInstance.class)
public class ItemInstanceMixin {

	private Random rand = new Random();
	
	@Shadow
	private int damage;

	@Inject(method = "applyDamage", at = @At("HEAD"))
	public void finalBeta_applyDamage(int i, EntityBase entityBase, CallbackInfo ci) {
		if(Config.ConfigFields.ADD_ITEM_BREAK_SOUNDS) {
			ItemInstance item = (ItemInstance) (Object) this;

			if (  (entityBase != null)
			   && (item != null)
			   && (this.damage + i > item.getDurability())
			   )
			{
				entityBase.level.playSound(entityBase, "random.break", 0.5f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}		
		}
	}
}
