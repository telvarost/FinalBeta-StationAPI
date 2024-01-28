package com.github.telvarost.finalbeta.mixin.options;

import com.github.telvarost.finalbeta.ModOptions;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.VideoSettings;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.gui.widgets.OptionButton;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

/** - All credit for the code in this class goes to Dany and his mod UniTweaks
 *  See: https://github.com/DanyGames2014/UniTweaks
 */
@Mixin(VideoSettings.class)
public class VideoSettingsMixin extends ScreenBase {
	@Shadow
	private static Option[] OPTIONS;

	@Shadow private GameOptions options;

	@Shadow private ScreenBase parent;

	static {
		OPTIONS = Arrays.copyOf(OPTIONS, OPTIONS.length + 3);
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 3] = ModOptions.fogDensityOption;
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 2] = ModOptions.cloudsOption;
		VideoSettingsMixin.OPTIONS[VideoSettingsMixin.OPTIONS.length - 1] = ModOptions.cloudHeightOption;
	}

	@Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
	protected void buttonClicked(Button arg, CallbackInfo ci) {
		if (arg.active) {
			if (arg.id < 100 && arg instanceof OptionButton) {
				this.options.changeOption(((OptionButton) arg).getOption(), 1);
				arg.text = this.options.getTranslatedValue(Option.getById(arg.id));
			}

			if (arg.id == 200) {
				this.minecraft.options.saveOptions();
				this.minecraft.openScreen(this.parent);
			}
		}
		ci.cancel();
	}
}
