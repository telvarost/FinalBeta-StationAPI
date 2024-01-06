package xyz.pixelatedw.finalbeta.mixin;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.container.ChestContainer;
import net.minecraft.entity.player.ClientPlayer;
import net.minecraft.entity.player.Player;
import net.minecraft.inventory.Inventory;
import xyz.pixelatedw.finalbeta.ModConfig;
import xyz.pixelatedw.finalbeta.WyHelper;

@Mixin(ClientPlayer.class)
public class ClientPlayerMixin {
	
	private Random rand = new Random();
	
	@Inject(method = "method_136", at = @At("TAIL"))
	public void onKeyPressed(int key, boolean state, CallbackInfo ci) {
		if (WyHelper.isDebug() && key == Keyboard.KEY_Z && state) {
			Player player = (Player) (Object) this;
			WyHelper.cheatCommand(player);
		}
	}
	
	@Inject(method = "openChestScreen", at = @At("TAIL"))
	public void openChestScreen(Inventory arg, CallbackInfo ci) {
		if(ModConfig.ADD_MORE_SOUNDS.get()) {
			Player player = (Player) (Object) this;
			player.level.playSound(player, "random.chestopen", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);			
		}
	}
	
	@Inject(method = "closeContainer", at = @At("HEAD"))
	public void closeContainer(CallbackInfo ci) {
		if(ModConfig.ADD_MORE_SOUNDS.get()) {
			Player player = (Player) (Object) this;
			if(player.container instanceof ChestContainer) {
				player.level.playSound(player, "random.chestclosed", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}
		}
	}
}
