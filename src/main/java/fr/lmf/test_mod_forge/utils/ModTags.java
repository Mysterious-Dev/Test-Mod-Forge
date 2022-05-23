package fr.lmf.test_mod_forge.utils;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> TEST_TOOL_TIER_TAG = BlockTags.create(new ResourceLocation(Main.MODID, "test_tier"));

    public static final TagKey<Block> TEST_TOOL_TYPE_TAG = BlockTags.create(new ResourceLocation(Main.MODID, "test_tool_type"));

}
