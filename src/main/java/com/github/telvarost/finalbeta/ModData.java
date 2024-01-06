package com.github.telvarost.finalbeta;

public class ModData {
    public static class ModDataFields {
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
//        public static boolean isDebug() {
//            return ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
//        }
//
//        public static float lerp(float delta, float start, float end) {
//            return start + delta * (end - start);
//        }
//
//        public static float clamp(float val, float min, float max) {
//            return val < min ? min : Math.min(val, max);
//        }
//
//        public static void cheatCommand(Player player) {
//
////		player.dropItem(new ItemInstance(Tile.SNOW));
////		Random rand = new Random();
////		player.level.playSound(player, "random.break", 1, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
//
////		player.dropItem(new ItemInstance(ItemType.hatchetDiamond, 1), false);
////		player.dropItem(new ItemInstance(ItemType.shovelDiamond, 1), false);
////		player.dropItem(new ItemInstance(Tile.CLAY, 128), false);
////		player.dropItem(new ItemInstance(Tile.GOLD_ORE, 64), false);
//
////		player.dropItem(new ItemInstance(ItemType.bow, 1), false);
////		player.dropItem(new ItemInstance(ItemType.arrow, 64), false);
////		player.dropItem(new ItemInstance(Tile.STONE, 64), false);
//
////		player.level.playLevelEvent((Player)null, 1005, (int)player.x, (int)player.y, (int)player.z, 0);
//
////		player.dropItem(new ItemInstance(Tile.REDSTONE_TORCH_LIT, 64), false);
////		player.dropItem(new ItemInstance(Tile.RAIL, 64), false);
////		player.dropItem(new ItemInstance(Tile.GOLDEN_RAIL, 64), false);
//
////		Zombie enemy = new Zombie(player.level);
////		enemy.setPositionAndAngles(player.x + 2, player.y, player.z, 0.0f, 0.0f);
////		player.level.spawnEntity(enemy);
//
////		player.level.setLevelTime(0);
////		player.level.getProperties().setRaining(false);
////		player.level.getProperties().setRainTime(0);
////		player.level.getProperties().setThundering(false);
////		player.level.getProperties().setThunderTime(0);
//        }
    }
}
