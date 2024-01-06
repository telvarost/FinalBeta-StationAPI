package xyz.pixelatedw.finalbeta.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.util.ResourceDownloadThread;

@Mixin(ResourceDownloadThread.class)
public class ResourceDownloadThreadMixin {
	
	private static final String RESOURCES_URL = "http://mcresources.modification-station.net/MinecraftResources/";
	
	@ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false)
	private String getResourcesUrl(String def) {
		return RESOURCES_URL;
	}
}