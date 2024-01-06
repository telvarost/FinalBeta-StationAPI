package xyz.pixelatedw.finalbeta.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemInstance;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(ItemInstance.class)
public class ItemInstanceMixin {

	private Random rand = new Random();
	
	@Shadow
	private int damage;

	@Inject(method = "applyDamage", at = @At("HEAD"))
	public void applyDamage(int i, Entity arg, CallbackInfo ci) {
		if(ModConfig.ADD_MORE_SOUNDS.get()) {
			ItemInstance item = (ItemInstance) (Object) this;
			if (this.damage + i > item.method_723()) {
				arg.level.playSound(arg, "random.break", 0.5f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}		
		}
	}
}
