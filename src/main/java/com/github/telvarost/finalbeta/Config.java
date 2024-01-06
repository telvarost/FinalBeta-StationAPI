package com.github.telvarost.finalbeta;

import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class Config {

    @GConfig(value = "config", visibleName = "FinalBeta Config")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigName("Sugarcane Fixes Enabled")
        public static Boolean sugarCaneFixesEnabled = true;
    }
}
