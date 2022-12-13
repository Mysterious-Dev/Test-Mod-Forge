package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TestBlockstateProvider extends BlockStateProvider {

    public TestBlockstateProvider(PackOutput packOutput, String modid, ExistingFileHelper exFileHelper) {
        super(packOutput, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.TEST_SIMPLE_BLOCK.get(), cubeAll(ModBlocks.TEST_SIMPLE_BLOCK.get()));
    }
}
