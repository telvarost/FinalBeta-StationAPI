package xyz.pixelatedw.finalbeta.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;

@Mixin(Screen.class)
public class ScreenMixin {
	
	@Shadow
	public Minecraft minecraft;
	
	@Shadow
	public List buttons;
}
