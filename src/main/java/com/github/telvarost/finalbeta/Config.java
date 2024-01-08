package com.github.telvarost.finalbeta;

import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Config {

    @GConfig(value = "config", visibleName = "FinalBeta Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Sugarcane Fixes Enabled")
        public static Boolean sugarCaneFixesEnabled = true;

        @ConfigName("FIX_BOW_MODEL")
        public static Boolean FIX_BOW_MODEL = true;

        @ConfigName("ADD_MORE_SOUNDS")
        public static Boolean ADD_MORE_SOUNDS = true;

        @ConfigName("FIX_FISHING")
        public static Boolean FIX_FISHING = true;

        @ConfigName("DEBUG_MODE")
        public static boolean isDebug = false;

//        public static final Option<Boolean> SUGAR_CANE_ON_SAND = make("Sugar Cane on sand", true, "Allows sugar canes to be placed on sand");
//        public static final Option<Boolean> ADD_MORE_SOUNDS = make("Add more sounds", true,
//                "Links a few more sounds from your local 'resources' folder with the game, namely for item breaking, minecarts and chests");
//        public static final Option<Boolean> ENABLE_CLOUDS = make("Enable Clouds", true, "Enables the rendering of clouds");
//        public static final Option<Boolean> DISABLE_ID_TAGS = make("Disable ID Tags", true,
//                "Disables id tags showing above entities in F3 mode");
//
//        public static final Option<Boolean> FIX_BOW_MODEL = make("Fix bow model", true,
//                "Makes the box model held by players and skeletons bigger and facing forward");
//        public static final Option<Boolean> FIX_MINECART_FLICKERING = make("Fix minecart flickering", true,
//                "Fixes minecarts flickering when looking at their backs as a passanger");
//        public static final Option<Boolean> FIX_MINECART_STOPPING_ON_ITEMS = make("Fix minecart stopping on items", true,
//                "Fixes minecarts getting stopped by arrows and dropped items on tracks");
//        public static final Option<Boolean> FIX_FISHING = make("Fix fishing", true, "Fixes fishes going above the player's head when fishing");
//        public static final Option<Boolean> FIX_LEG_ARMOR_ON_VEHICLES = make("Fix leg armor on vehicles", true,
//                "Fixes leg armor not properly getting updated when switching poses (start/stop riding a vehicle)");
//        public static final Option<Boolean> FIX_STAIRS_DROPS = make("Fix stairs drops", true, "Fix stairs not dropping themselves when mined");
//        public static final Option<Boolean> FIX_PICKAXE_EFFECTIVENESS = make("Fix pickaxe effectiveness", true,
//                "Fixes pickaxes not being effective agaist certain blocks that it should be effective on");
//        public static final Option<Boolean> FIX_AXE_EFFECTIVENESS = make("Fix axe effectiveness", true,
//                "Fixes axes not being effective agaist certain blocks that it should be effective on");
//        public static final Option<Boolean> FIX_SADDLES_NOT_DROPPING = make("Fix saddles not dropping", true,
//                "Fixes saddles not dropping when killing saddled pigs");
//        public static final Option<Boolean> FIX_FURNACE_LAVA_BUCKET = make("Fix furnace lava bucket", true,
//                "Fixes furnaces consuming the bucket when using lava buckets as fuel");
    }
}
