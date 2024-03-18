package com.github.telvarost.finalbeta;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "FinalBeta")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigCategory("Graphical Fixes")
        public GraphicsConfig GRAPHICS_CONFIG = new GraphicsConfig();

        @ConfigCategory("Score Config")
        public ScoreConfig SCORE_CONFIG = new ScoreConfig();

        @ConfigName("Add sound: Item Breaking")
        public Boolean ADD_ITEM_BREAK_SOUNDS = true;

        @ConfigName("Add sound: Minecart Rolling")
        public Boolean ADD_MINECART_SOUNDS = true;

        @ConfigName("Add sound: Open/Close Chest")
        public Boolean ADD_CHEST_SOUNDS = true;

        @ConfigName("Allow placing pressure plates on fences")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean ALLOW_PRESSURE_PLATES_ON_FENCES = true;

        @ConfigName("Allow placing sugarcane on sand")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean ALLOW_SUGAR_CANE_ON_SAND = true;

        @ConfigName("Allow placing trapdoors without support")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean ALLOW_TRAPDOORS_WITHOUT_SUPPORTS = true;

        @ConfigName("Disable beds")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean DISABLE_BEDS = false;

        @ConfigName("Disable nightmares")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean DISABLE_NIGHTMARES = false;

        @ConfigName("Fix caught fish going above player head")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean FIX_FISHING = true;

        @ConfigName("Fix lava bucket being consumed in furnace")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean FIX_FURNACE_LAVA_BUCKET = true;

        @ConfigName("Fix minecart stopping on items")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean FIX_MINECART_STOPPING_ON_ITEMS = true;

        /** - At the current moment this is not toggle-able */
//        @ConfigName("Improved option/video menu controls")
//        @Comment("Adds controls for clouds, fog, and FOV")
//        public static Boolean IMPROVED_MENU_CONTROLS = true;

        @ConfigName("Use \"Shift + DROP_KEY\" to drop item stack")
        public Boolean STACK_DROP = false;

        @ConfigName("Use \"Shift\" to exit vehicles")
        public Boolean SHIFT_EXIT_VEHICLES = true;
    }

    public static class GraphicsConfig {

        @ConfigName("Add day counter to F3 overlay")
        public Boolean ADD_DAY_COUNTER = true;

        @ConfigName("Disable id tags")
        public Boolean DISABLE_ID_TAGS = true;

        @ConfigName("Fix bow models")
        public Boolean FIX_BOW_MODEL = true;

        @ConfigName("Fix container label rendering")
        public Boolean FIX_CONTAINER_LABEL_RENDERING = true;

        @ConfigName("Fix death screen text")
        public Boolean FIX_DEATH_SCREEN_TEXT = true;

        @ConfigName("Fix leg armor on vehicles")
        public Boolean FIX_LEG_ARMOR_ON_VEHICLES = true;

        @ConfigName("Fix minecart flickering")
        public Boolean FIX_MINECART_FLICKERING = true;
    }

    public static class ScoreConfig {

        @ConfigName("Each block placed adds +1 to score")
        public Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigName("Each block removed adds +1 to score")
        public Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigName("Each monster mob killed adds +1 to score")
        public Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigName("Each passive mob killed adds +1 to score")
        public Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }
}
