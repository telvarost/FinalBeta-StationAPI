package com.github.telvarost.finalbeta;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

import java.util.Random;

public class ModData {

    public static void cheatCommand(PlayerBase player) {

        player.dropItem(new ItemInstance(BlockBase.SNOW));
        Random rand = new Random();
        player.level.playSound(player, "random.break", 1, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);

        player.dropItem(new ItemInstance(ItemBase.diamondAxe, 1), false);
        player.dropItem(new ItemInstance(ItemBase.diamondShovel, 1), false);
        player.dropItem(new ItemInstance(BlockBase.CLAY, 128), false);
        player.dropItem(new ItemInstance(BlockBase.GOLD_ORE, 64), false);

        player.dropItem(new ItemInstance(ItemBase.bow, 1), false);
        player.dropItem(new ItemInstance(ItemBase.arrow, 64), false);
        player.dropItem(new ItemInstance(BlockBase.STONE, 64), false);

        player.level.playLevelEvent((PlayerBase)null, 1005, (int)player.x, (int)player.y, (int)player.z, 0);

        player.dropItem(new ItemInstance(BlockBase.REDSTONE_TORCH_LIT, 64), false);
        player.dropItem(new ItemInstance(BlockBase.RAIL, 64), false);
        player.dropItem(new ItemInstance(BlockBase.GOLDEN_RAIL, 64), false);

//            Zombie enemy = new Zombie(player.level);
//            enemy.setPositionAndAngles(player.x + 2, player.y, player.z, 0.0f, 0.0f);
//            player.level.spawnEntity(enemy);

        player.level.setLevelTime(0);
        player.level.getProperties().setRaining(false);
        player.level.getProperties().setRainTime(0);
        player.level.getProperties().setThundering(false);
        player.level.getProperties().setThunderTime(0);
    }

    public static class ModDataFields {

        public static boolean isDebug = true;

//        public static Minecraft getInstance()  {
//            try {
//                Field f = Minecraft.class.getDeclaredField("instance");
//                f.setAccessible(true);
//                return (Minecraft) f.get(null);
//            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        public static boolean isTimeBehind() {
//            // int currentPlayTime = WyHelper.getInstance().statManager.getStatAmount(Stats.playOneMinute)
//            return false;
//        }
//
//        public static long getTicksPlayed() {
//            return WyHelper.getInstance().statManager.getStatAmount(Stats.playOneMinute);
//        }
//
//        public static long getRealDaysPlayed() {
//            int seconds = WyHelper.getInstance().statManager.getStatAmount(Stats.playOneMinute) / 20;
//            return Duration.ofSeconds(seconds).toDays();
//        }
//
//        public static long getGameDaysPlayed() {
//            int seconds = WyHelper.getInstance().statManager.getStatAmount(Stats.playOneMinute) / 20;
//            return Duration.ofSeconds(seconds).toMinutes() / 20;
//        }
//

//
//        public static float lerp(float delta, float start, float end) {
//            return start + delta * (end - start);
//        }
//
//        public static float clamp(float val, float min, float max) {
//            return val < min ? min : Math.min(val, max);
//        }
//
    }
}
