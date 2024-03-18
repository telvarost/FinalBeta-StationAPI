package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Trapdoor;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Trapdoor.class)
public class TrapdoorMixin extends BlockBase {

    public TrapdoorMixin(int i, Material arg) {
        super(i, arg);
    }

    @Redirect(
            method = "onAdjacentBlockUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;canSuffocate(III)Z"
            )
    )
    public boolean finalBeta_onAdjacentBlockUpdate(Level instance, int i, int j, int k) {
        if (Config.config.ALLOW_TRAPDOORS_WITHOUT_SUPPORTS) {
            return true;
        } else {
            return instance.canSuffocate(i, j, k);
        }
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void finalBeta_canPlaceAt(Level arg, int i, int j, int k, int l, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.ALLOW_TRAPDOORS_WITHOUT_SUPPORTS) {
            cir.setReturnValue(!arg.getMaterial(i, j, k).isSolid());
        }
    }
}
