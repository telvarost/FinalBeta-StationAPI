package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.PlayerKeypressManager;
import net.minecraft.client.gui.screen.container.DoubleChest;
import net.minecraft.client.util.Session;
import net.minecraft.client.util.Smoother;
import net.minecraft.container.Chest;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin extends PlayerBase {
    @Shadow public PlayerKeypressManager playerKeypressManager;
    @Shadow protected Minecraft minecraft;
    @Shadow private Smoother field_163 = new Smoother();
    @Shadow private Smoother field_164 = new Smoother();
    @Shadow private Smoother field_165 = new Smoother();

    public AbstractClientPlayerMixin(Minecraft minecraft, Level arg, Session arg2, int i) {
        super(arg);
        this.minecraft = minecraft;
        this.dimensionId = i;
        if (arg2 != null && arg2.username != null && arg2.username.length() > 0) {
            this.skinUrl = "http://s3.amazonaws.com/MinecraftSkins/" + arg2.username + ".png";
        }

        this.name = arg2.username;
    }

    @Inject(method = "method_136", at = @At("TAIL"))
    public void finalBeta_method_136(int key, boolean state, CallbackInfo ci) {
        PlayerBase player = (PlayerBase) (Object) this;

        if (  (Config.config.SHIFT_EXIT_VEHICLES)
           && (player.vehicle != null)
           && (state)
           && (key == Keyboard.KEY_LSHIFT)
        ) {
            player.startRiding(null);
            return;
        }

        if (  (Config.config.STACK_DROP)
           && (key == this.minecraft.options.dropKey.key)
           && (state)
           && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        ) {
            ItemInstance heldItem = player.inventory.getHeldItem();
            if (heldItem != null && heldItem.count > 0) {
                player.dropItem(player.inventory.takeInventoryItem(player.inventory.selectedHotbarSlot, heldItem.count), false);
                return;
            }
        }
    }

    public void method_494() {
    }

    @Inject(method = "openChestScreen", at = @At("TAIL"))
	public void finalBeta_openChestScreen(InventoryBase arg, CallbackInfo ci) {
		this.minecraft.openScreen(new DoubleChest(this.inventory, arg));

		if(Config.config.ADD_CHEST_SOUNDS) {
			PlayerBase player = (PlayerBase) (Object) this;
			player.level.playSound(player, "random.chestopen", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		}
	}

	@Inject(method = "closeContainer", at = @At("HEAD"))
	public void finalBeta_closeContainer(CallbackInfo ci) {
		if(Config.config.ADD_CHEST_SOUNDS) {
			PlayerBase player = (PlayerBase) (Object) this;
			if(player.container instanceof Chest) {
				player.level.playSound(player, "random.chestclosed", 0.3f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			}
		}
	}
}
