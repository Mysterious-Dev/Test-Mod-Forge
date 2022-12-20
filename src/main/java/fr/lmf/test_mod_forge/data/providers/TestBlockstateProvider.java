package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.block.TestBlockStateBlock;
import fr.lmf.test_mod_forge.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class TestBlockstateProvider extends BlockStateProvider {

    public TestBlockstateProvider(PackOutput packOutput, String modid, ExistingFileHelper exFileHelper) {
        super(packOutput, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.TEST_SIMPLE_BLOCK.get(), cubeAll(ModBlocks.TEST_SIMPLE_BLOCK.get()));

        getVariantBuilder(ModBlocks.TEST_BLOCKSTATE_BLOCK.get())
                .partialState()
                    .with(TestBlockStateBlock.TEST_PROPERTY, true)
                    .modelForState()
                    .modelFile(models().cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.TEST_BLOCKSTATE_BLOCK.get()).getPath() + "_activated", new ResourceLocation(Main.MODID, blockTexture(ModBlocks.TEST_BLOCKSTATE_BLOCK.get()).getPath() + "_activated")))
                    .addModel()
                .partialState()
                .with(TestBlockStateBlock.TEST_PROPERTY, false)
                .modelForState()
                .modelFile(models().cubeAll(ForgeRegistries.BLOCKS.getKey(ModBlocks.TEST_BLOCKSTATE_BLOCK.get()).getPath(),blockTexture(ModBlocks.TEST_BLOCKSTATE_BLOCK.get())))
                .addModel();
    }
}
