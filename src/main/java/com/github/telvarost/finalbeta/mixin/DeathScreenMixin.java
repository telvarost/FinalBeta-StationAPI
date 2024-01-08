package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.gui.screen.ingame.Death;

@Mixin(Death.class)
public class DeathScreenMixin {
	@ModifyConstant(method = "render", constant = @Constant(stringValue = "Score: &e"))
	private String getResourcesUrl(String def) {
		return def.replace('&', '\u00a7'); // Replacing & with § so the color actually displays as yellow
	}
}