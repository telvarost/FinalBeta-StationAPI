package com.github.telvarost.finalbeta.mixin.options;

import com.github.telvarost.finalbeta.ModOptions;
import net.minecraft.client.gui.screen.menu.VideoSettings;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

/** - All credit for the code in this class goes to Dany and his mod UniTweaks
 *  See: https://github.com/DanyGames2014/UniTweaks
 */
@Mixin(VideoSettings.class)
public class VideoSettingsMixin {
	@Shadow
	private static Option[] OPTIONS;

	static {
		OPTIONS = Arrays.copyOf(OPTIONS, OPTIONS.length + 3);
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 3] = ModOptions.fogDensityOption;
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 2] = ModOptions.cloudsOption;
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 1] = ModOptions.cloudHeightOption;
	}
}
