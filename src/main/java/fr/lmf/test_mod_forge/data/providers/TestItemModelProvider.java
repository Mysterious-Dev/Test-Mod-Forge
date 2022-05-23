package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TestItemModelProvider extends ItemModelProvider {
    public TestItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("test_simple_block")
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Main.MODID, "block/test_simple_block")));

        getBuilder("test_tool")
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/test_tool"));

        getBuilder("test_pickaxe")
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/test_pickaxe"));

    }
}
