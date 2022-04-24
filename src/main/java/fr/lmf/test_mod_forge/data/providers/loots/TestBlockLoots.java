package fr.lmf.test_mod_forge.data.providers.loots;

import fr.lmf.test_mod_forge.init.ModBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class TestBlockLoots extends BlockLoot {

    @Override
    protected void addTables() {
        dropSelf(ModBlocks.TEST_SIMPLE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
