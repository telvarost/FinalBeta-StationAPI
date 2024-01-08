package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.container.DoubleChest;
import net.minecraft.client.util.Session;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.level.Level;
import net.minecraft.network.ClientPlayNetworkHandler;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.container.Chest;
import net.minecraft.entity.player.ClientPlayer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;

@Mixin(ClientPlayer.class)
public class ClientPlayerMixin extends AbstractClientPlayer {
	
	private Random rand = new Random();

	@Shadow
	public ClientPlayNetworkHandler networkHandler;

	public ClientPlayerMixin(Minecraft minecraft, Level arg, Session arg2, ClientPlayNetworkHandler arg3) {
		super(minecraft, arg, arg2, 0);
		this.networkHandler = arg3;
	}

	@Override
	public void method_136(int i, boolean bl) {
		this.playerKeypressManager.onKeyPressed(i, bl);

		if (Config.ConfigFields.isDebug && i == Keyboard.KEY_Z && bl) {
			PlayerBase player = (PlayerBase) (Object) this;
			ModHelper.cheatCommand(player);
		}
	}

	@Override
	public void openChestScreen(InventoryBase arg) {
		this.minecraft.openScreen(new DoubleChest(this.inventory, arg));

		if(Config.ConfigFields.ADD_MORE_SOUNDS) {
			PlayerBase player = (PlayerBase) (Object) this;
			player.level.playSound(player, "random.chestopen", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);			
		}
	}
	
	@Inject(method = "closeContainer", at = @At("HEAD"))
	public void closeContainer(CallbackInfo ci) {
		if(Config.ConfigFields.ADD_MORE_SOUNDS) {
			PlayerBase player = (PlayerBase) (Object) this;
			if(player.container instanceof Chest) {
				player.level.playSound(player, "random.chestclosed", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}
		}
	}
}
