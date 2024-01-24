package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "loadSoundFromDir", at = @At("HEAD"))
	public void finalBeta_loadSoundFromDir(String string, File file, CallbackInfo ci) {
		Minecraft mc = (Minecraft) (Object) this;
		int split = string.indexOf("/");
		String type = string.substring(0, split);
		String newSound = string.substring(split + 1);
		// For now only allow the minecart sounds, allowing all of them causes weird effects with same name sounds when the game decides which one to use
		// XXX Could always incorporate the sound3 part into the sound's name and have it accessible as sound3.random.bow for example, which would avoid the overlap with current sounds
		if (type.equalsIgnoreCase("sound3") && newSound.startsWith("minecart/")) {
			mc.soundHelper.addSound(newSound, file);
		}
	}
}
