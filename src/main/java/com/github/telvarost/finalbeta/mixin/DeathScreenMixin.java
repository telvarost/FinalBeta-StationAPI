package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.gui.screen.DeathScreen;

@Mixin(DeathScreen.class)
public class DeathScreenMixin {
	@ModifyConstant(method = "render", constant = @Constant(stringValue = "Score: &e"))
	private String getResourcesUrl(String def) {
		return def.replace('&', '\u00a7'); // Replacing & with § so the color actually displays as yellow
	}
}