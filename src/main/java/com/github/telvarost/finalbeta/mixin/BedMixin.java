package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import net.minecraft.block.Bed;
import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.level.Level;

@Mixin(Bed.class)
public class BedMixin {

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void finalBeta_canUse(Level level, int x, int y, int z, PlayerBase player, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.DISABLE_BEDS) {
            cir.setReturnValue(false);
        }
    }

}
