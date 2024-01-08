package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;

@Mixin(ScreenBase.class)
public class ScreenMixin {
	
	@Shadow
	public Minecraft minecraft;
	
	@Shadow
	public List buttons;
}
