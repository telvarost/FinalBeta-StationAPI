package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import net.minecraft.block.BlockBase;
import net.minecraft.block.SugarCane;
import net.minecraft.block.material.Material;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SugarCane.class)
class SugarCaneMixin extends BlockBase {

    public SugarCaneMixin(int i, int j) {
        super(i, Material.PLANT);
        this.texture = j;
        float var3 = 0.375F;
        this.setBoundingBox(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
        this.setTicksRandomly(true);
    }

    @Redirect(
            method = "canPlaceAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;getTileId(III)I"
            )
    )
    public int finalBeta_canPlaceAt(Level arg, int i, int j, int k) {
        if (Config.config.ALLOW_SUGAR_CANE_ON_SAND)
        {
            int tileToPlaceAt = arg.getTileId(i, j, k);

            /** - Treat sand like dirt so that sugarcane can be place-able */
            if (BlockBase.SAND.id == tileToPlaceAt)
            {
                tileToPlaceAt = BlockBase.DIRT.id;
            }

            return tileToPlaceAt;
        }
        else
        {
            return arg.getTileId(i, j, k);
        }
    }
}

