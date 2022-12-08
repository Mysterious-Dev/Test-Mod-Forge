package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.init.ModBlocks;
import fr.lmf.test_mod_forge.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TestBlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public TestBlockTagProvider(PackOutput p_256406_, CompletableFuture<HolderLookup.Provider> p_256525_) {
        super(p_256406_, Registries.BLOCK, p_256525_, (p_255627_) -> {
            return p_255627_.builtInRegistryHolder().key();
        });
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TEST_SIMPLE_BLOCK.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TEST_SIMPLE_BLOCK.get());

        tag(ModTags.TEST_TOOL_TYPE_TAG)
                .add(ModBlocks.TEST_SIMPLE_BLOCK.get());
    }
}
