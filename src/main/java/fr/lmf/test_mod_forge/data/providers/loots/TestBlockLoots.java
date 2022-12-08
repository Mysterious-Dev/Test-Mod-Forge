package fr.lmf.test_mod_forge.data.providers.loots;

import fr.lmf.test_mod_forge.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import java.util.Set;

public class TestBlockLoots extends BlockLootSubProvider {

    public TestBlockLoots() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.TEST_SIMPLE_BLOCK.get());
    }
}
