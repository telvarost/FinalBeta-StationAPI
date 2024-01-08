package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.util.ThreadDownloadResources;

@Mixin(ThreadDownloadResources.class)
public class ResourceDownloadThreadMixin {
	
	private static final String RESOURCES_URL = "http://mcresources.modification-station.net/MinecraftResources/";
	
	@ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false)
	private String finalBeta_getResourcesUrl(String def) {
		return RESOURCES_URL;
	}
}