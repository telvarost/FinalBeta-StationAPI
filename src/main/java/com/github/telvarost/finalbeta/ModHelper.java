package com.github.telvarost.finalbeta;

import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.stat.Stats;
import net.minecraft.client.Minecraft;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Random;

public class ModHelper {

    public static Minecraft getInstance()  {
        try {
            Field f = Minecraft.class.getDeclaredField("instance");
            f.setAccessible(true);
            return (Minecraft) f.get(null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getRealDaysPlayed() {
        int seconds = ModHelper.getInstance().statFileWriter.write(Stats.playOneMinute) / 20;
        return Duration.ofSeconds(seconds).toDays();
    }

    public static long getGameDaysPlayed() {
        int seconds = ModHelper.getInstance().statFileWriter.write(Stats.playOneMinute) / 20;
        return Duration.ofSeconds(seconds).toMinutes() / 20;
    }

    public static float lerp(float delta, float start, float end) {
        return start + delta * (end - start);
    }

    public static float clamp(float val, float min, float max) {
        return val < min ? min : Math.min(val, max);
    }

    public static class ModHelperFields {

        public static Boolean ENABLE_CLOUDS = true;

        public static Boolean IS_LAVA_BUCKET_CONSUMED = false;
    }
}
