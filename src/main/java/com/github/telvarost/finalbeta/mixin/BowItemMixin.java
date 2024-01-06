package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.item.ItemType;
import net.minecraft.item.tool.BowItem;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(BowItem.class)
public class BowItemMixin {
	@Inject(method = "<init>(I)V", at = @At("TAIL"))
	public void init(int i, CallbackInfo ci) {
		if(ModConfig.FIX_BOW_MODEL.get()) {
			ItemType item = ((ItemType) (Object) this);
			item.method_466();			
		}
	}
}
