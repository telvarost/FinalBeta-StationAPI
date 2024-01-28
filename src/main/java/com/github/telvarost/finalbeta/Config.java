package com.github.telvarost.finalbeta;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Config {

    @GConfig(value = "config", visibleName = "FinalBeta Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigCategory("Graphical Fixes")
        public static GraphicsConfig GRAPHICS_CONFIG = new GraphicsConfig();

        @ConfigCategory("Score Config")
        public static ScoreConfig SCORE_CONFIG = new ScoreConfig();

        @ConfigName("Add day counter to F3 overlay")
        public static Boolean ADD_DAY_COUNTER = true;

        @ConfigName("Add sound: Item Breaking")
        public static Boolean ADD_ITEM_BREAK_SOUNDS = true;

        @ConfigName("Add sound: Minecart Rolling")
        public static Boolean ADD_MINECART_SOUNDS = true;

        @ConfigName("Add sound: Open/Close Chest")
        public static Boolean ADD_CHEST_SOUNDS = true;

        @ConfigName("Allow placing pressure plates on fences")
        public static Boolean ALLOW_PRESSURE_PLATES_ON_FENCES = true;

        @ConfigName("Allow placing sugarcane on sand")
        public static Boolean ALLOW_SUGAR_CANE_ON_SAND = true;

        @ConfigName("Disable beds")
        public static Boolean DISABLE_BEDS = false;

        @ConfigName("Disable id tags")
        public static Boolean DISABLE_ID_TAGS = true;

        @ConfigName("Disable nightmares")
        public static Boolean DISABLE_NIGHTMARES = false;

        @ConfigName("Fix caught fish going above player head")
        public static Boolean FIX_FISHING = true;

        @ConfigName("Fix lava bucket being consumed in furnace")
        public static Boolean FIX_FURNACE_LAVA_BUCKET = true;

        @ConfigName("Fix minecart stopping on items")
        public static Boolean FIX_MINECART_STOPPING_ON_ITEMS = true;

        /** - At the current moment this is not toggle-able */
//        @ConfigName("Improved option/video menu controls")
//        @Comment("Adds controls for clouds, fog, and FOV")
//        public static Boolean IMPROVED_MENU_CONTROLS = true;

        @ConfigName("Use \"Shift + DROP_KEY\" to drop item stack")
        public static Boolean STACK_DROP = false;

        @ConfigName("Use \"Shift\" to exit vehicles")
        public static Boolean SHIFT_EXIT_VEHICLES = true;
    }

    public static class GraphicsConfig {

        @ConfigName("Fix bow models")
        public static Boolean FIX_BOW_MODEL = true;

        @ConfigName("Fix container label rendering")
        public static Boolean FIX_CONTAINER_LABEL_RENDERING = true;

        @ConfigName("Fix death screen text")
        public static Boolean FIX_DEATH_SCREEN_TEXT = true;

        @ConfigName("Fix leg armor on vehicles")
        public static Boolean FIX_LEG_ARMOR_ON_VEHICLES = true;

        @ConfigName("Fix minecart flickering")
        public static Boolean FIX_MINECART_FLICKERING = true;
    }

    public static class ScoreConfig {

        @ConfigName("Each block placed adds +1 to score")
        public static Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigName("Each block removed adds +1 to score")
        public static Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigName("Each monster mob killed adds +1 to score")
        public static Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigName("Each passive mob killed adds +1 to score")
        public static Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }
}
