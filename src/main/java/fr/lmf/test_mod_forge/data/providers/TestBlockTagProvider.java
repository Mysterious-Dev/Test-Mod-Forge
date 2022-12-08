package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.init.ModBlocks;
import fr.lmf.test_mod_forge.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TestBlockTagProvider extends BlockTagsProvider {
    public TestBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Main.MODID, existingFileHelper);
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
