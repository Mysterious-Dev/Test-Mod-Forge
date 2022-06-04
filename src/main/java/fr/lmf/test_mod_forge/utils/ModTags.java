package fr.lmf.test_mod_forge.utils;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> TEST_TOOL_TIER_TAG = BlockTags.create(new ResourceLocation(Main.MODID, "test_tier"));

    public static final TagKey<Block> TEST_TOOL_TYPE_TAG = BlockTags.create(new ResourceLocation(Main.MODID, "test_tool_type"));

    public static final TagKey<Biome> HAS_ABANDONNED_HOUSE = create("has_abandonned_house");

    private static TagKey<Biome> create(String p_207631_) {
        return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Main.MODID, p_207631_));
    }

}
