package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Door;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.level.Level;

@Mixin(Door.class)
public class DoorMixin extends BlockBase {

    protected DoorMixin(int i, int j, Material arg) {
        super(i, j, arg);
    }

    /*
     * This is horrible and with edge cases, not the best of fixes, needs more
     * work preferably changing the metadata of doors to allow for more states.
     *
     * When times get hard always remember:
     * "If it's stupid but it works, it's still stupid but it works so fuck it."
     */

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    public void finalBeta_canUse(Level level, int x, int y, int z, PlayerBase player, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.ConfigFields.FIX_DOUBLE_DOORS) {
            return;
        }

        Door tile = ((Door) (Object) this);
        if (tile.material != Material.METAL) {
            int tileMeta = level.getTileMeta(x, y, z);
            int yOffset = 0;

            if ((tileMeta & 8) != 0) {
                setDoorTileMeta(level, x, y - 1, z, (tileMeta ^ 4) - 8);
                yOffset = 1;
            } else {
                setDoorTileMeta(level, x, y, z, tileMeta ^ 4);
            }

            boolean state = false;
            int hash = generatePosHash(x, y - yOffset, z);
            if (ModHelper.ModHelperFields.DOOR_STATES.containsKey(hash)) {
                state = ModHelper.ModHelperFields.DOOR_STATES.get(hash);
            }
            ModHelper.ModHelperFields.DOOR_STATES.put(hash, !state);
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "method_837", at = @At("HEAD"), cancellable = true)
    public void updateRedstoneSignal(Level level, int x, int y, int z, boolean openSignal, CallbackInfo ci) {

        if (!Config.ConfigFields.FIX_DOUBLE_DOORS) {
            return;
        }

        // Top Tile Meta
        // Open: 12 11 - 13 8 - 14 9 - 15 10
        // Close: 8 15 - 9 12 - 10 13 - 11 14

        // Bottom Tile Meta
        // Open: 4 3 - 5 0 - 6 1 - 7 2
        // Close: 0 7 - 1 4 - 2 5 - 3 6

        int hash = generatePosHash(x, y, z);
        if (ModHelper.ModHelperFields.DOOR_UPDATES.containsKey(hash)) {
            long lastUpdate = System.currentTimeMillis() - ModHelper.ModHelperFields.DOOR_UPDATES.get(hash);
            if (lastUpdate < 200) {
                ci.cancel();
                return;
            }
        }

        int tileMeta = level.getTileMeta(x, y, z);

        if (openSignal) {
            if (ModHelper.ModHelperFields.DOOR_STATES.containsKey(hash) && ModHelper.ModHelperFields.DOOR_STATES.get(hash)) {
                ci.cancel();
                return;
            }
            setDoorTileMeta(level, x, y, z, Math.floorMod((tileMeta + 4), 8));
            ModHelper.ModHelperFields.DOOR_STATES.put(hash, true);
        } else {
            if (ModHelper.ModHelperFields.DOOR_STATES.containsKey(hash) && !ModHelper.ModHelperFields.DOOR_STATES.get(hash)) {
                ci.cancel();
                return;
            }
            setDoorTileMeta(level, x, y, z, Math.floorMod((tileMeta - 4), 8));
            ModHelper.ModHelperFields.DOOR_STATES.put(hash, false);
        }

        ci.cancel();
    }

    @Override
    public void beforeDestroyedByExplosion(Level level, int x, int y, int z, int i1, float f) {
        int tileMeta = level.getTileMeta(x, y, z);
        int yOffset = 0;

        if ((tileMeta & 8) != 0) {
            yOffset = 1;
        }

        int hash = generatePosHash(x, y - yOffset, z);
        ModHelper.ModHelperFields.DOOR_UPDATES.remove(hash);
        ModHelper.ModHelperFields.DOOR_STATES.remove(hash);
        super.beforeDestroyedByExplosion(level, x, y, z, i1, f);
    }

    @Override
    public void afterPlaced(Level level, int x, int y, int z, Living entity) {
        int hash = generatePosHash(x, y, z);
        ModHelper.ModHelperFields.DOOR_STATES.put(hash, false);
    }

    private void setDoorTileMeta(Level level, int x, int y, int z, int meta) {
        int tileId = level.getTileId(x, y, z);
        level.setTileMeta(x, y, z, meta);
        generateAndSaveHash(x, y, z);
        if (level.getTileId(x, y + 1, z) == tileId) {
            level.setTileMeta(x, y + 1, z, meta + 8);
            generateAndSaveHash(x, y, z);
        }

        level.method_202(x, y - 1, z, x, y, z);
        level.playLevelEvent((PlayerBase) null, 1003, x, y, z, 0);
    }

    public int generatePosHash(int x, int y, int z) {
        return (y * 31) + (z * 15) + x;
    }

    private void generateAndSaveHash(int x, int y, int z) {
        ModHelper.ModHelperFields.DOOR_UPDATES.put(generatePosHash(x, y, z), System.currentTimeMillis());
    }
}
