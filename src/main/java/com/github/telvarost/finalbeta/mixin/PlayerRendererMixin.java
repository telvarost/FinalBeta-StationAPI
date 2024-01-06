package xyz.pixelatedw.finalbeta.mixin;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.client.render.entity.model.BipedModel;
import net.minecraft.entity.player.Player;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.item.armour.ArmourItem;
import xyz.pixelatedw.finalbeta.ModConfig;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin extends LivingEntityRendererMixin {

	@Shadow
	private BipedModel field_295; // Armor

	@Shadow
	private BipedModel field_296; // Legs

	@Inject(method = "method_827", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/HandItemRenderer;method_1862(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemInstance;)V", shift = Shift.BEFORE))
	public void playerRendering(Player player, float f, CallbackInfo ci) {
		if (ModConfig.FIX_BOW_MODEL.get()) {
			ItemInstance item = player.inventory.getHeldItem();
			if (item != null && item.itemId == ItemType.bow.id) {
				GL11.glTranslatef(0.0F, -0.5F, 0.0F);
			}
		}
	}

	@Inject(method = "render(Lnet/minecraft/entity/player/Player;DDDFF)V", at = @At("HEAD"))
	public void render(Player arg, double d, double d1, double d2, float f, float f1, CallbackInfo ci) {
		if (ModConfig.FIX_LEG_ARMOR_ON_VEHICLES.get()) {
			ItemInstance stack = arg.inventory.getArmourItem(1);
			if (stack != null) {
				ItemType item = stack.getType();
				if (item instanceof ArmourItem) {
					this.field_296.isRiding = this.field_909.isRiding;
				}
			}
		}
	}
}
