package fr.lmf.test_mod_forge.utils;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public static final ForgeConfigSpec.Builder CLIENT_CONFIG = new ForgeConfigSpec.Builder();

    static {
        CLIENT_CONFIG.push("category_one");

        CLIENT_CONFIG.define("test_boolean", true);

        CLIENT_CONFIG.pop();
    }

}
