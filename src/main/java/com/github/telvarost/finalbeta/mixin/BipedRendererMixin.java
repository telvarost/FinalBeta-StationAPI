package xyz.pixelatedw.finalbeta.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(BipedEntityRenderer.class)
public class BipedRendererMixin {

	@Inject(method = "method_827", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/HandItemRenderer;method_1862(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemInstance;)V", shift = Shift.BEFORE))
	public void playerRendering(LivingEntity entity, float f, CallbackInfo ci) {
		if(ModConfig.FIX_BOW_MODEL.get()) {
			ItemInstance item = entity.getHandRenderItem();
			if (item != null && item.itemId == ItemType.bow.id) {
				GL11.glRotatef(-5, 1, 0, 0);
				GL11.glTranslatef(0.2F, -0.5F, 0.2F);
			}			
		}
	}
}
