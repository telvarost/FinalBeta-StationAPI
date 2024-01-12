package com.github.telvarost.finalbeta.mixin;

import com.github.telvarost.finalbeta.Config;
import com.github.telvarost.finalbeta.ModHelper;
import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.level.LevelProperties;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {

    private long spawnTime;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void settingSpawnTime(CompoundTag nbt, CallbackInfo ci) {
        if (!nbt.containsKey(ModHelper.ModHelperFields.SPAWN_TIME_TAG)) {
            this.spawnTime = System.currentTimeMillis();
        } else {
            this.spawnTime = nbt.getLong(ModHelper.ModHelperFields.SPAWN_TIME_TAG);
        }
        ModHelper.ModHelperFields.playTime = nbt.getLong(ModHelper.ModHelperFields.PLAY_TIME_TAG);

        if (Config.ConfigFields.FIX_DOUBLE_DOORS) {
            ModHelper.ModHelperFields.DOOR_STATES.clear();
            ListTag doorStates = nbt.getListTag("DoorStates");
            for (int i = 0; i < doorStates.size(); i++) {
                CompoundTag doorTag = (CompoundTag) doorStates.get(i);
                int hash = doorTag.getInt("hash");
                boolean state = doorTag.getBoolean("state");
                ModHelper.ModHelperFields.DOOR_STATES.put(hash, state);
            }
        }

    }

    @Inject(method = "updateProperties", at = @At("TAIL"))
    public void updateSpawnTime(CompoundTag worldNbt, CompoundTag playerNbt, CallbackInfo ci) {
        worldNbt.put(ModHelper.ModHelperFields.SPAWN_TIME_TAG, this.spawnTime);
        worldNbt.put(ModHelper.ModHelperFields.PLAY_TIME_TAG, ModHelper.ModHelperFields.playTime);

        if (Config.ConfigFields.FIX_DOUBLE_DOORS) {
            ListTag doorStates = new ListTag();
            for (Entry<Integer, Boolean> entry : ModHelper.ModHelperFields.DOOR_STATES.entrySet()) {
                CompoundTag doorTag = new CompoundTag();
                doorTag.put("hash", entry.getKey());
                doorTag.put("state", entry.getValue());
                doorStates.add(doorTag);
            }
            worldNbt.put("DoorStates", doorStates);
        }
    }
}
