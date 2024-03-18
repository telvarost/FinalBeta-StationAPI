package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import java.util.List;

import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.level.Level;
import net.minecraft.sortme.LevelMonsterSpawner;

@Mixin(LevelMonsterSpawner.class)
public class LevelMonsterSpawnerMixin {

    @Inject(method = "method_1869", at = @At("HEAD"), cancellable = true)
    private static void nightmaresHandler(Level level, List<PlayerBase> playersList, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.DISABLE_NIGHTMARES) {
            cir.setReturnValue(false);
        }
    }
}
