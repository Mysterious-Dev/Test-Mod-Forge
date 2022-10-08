package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.init.ModItems;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
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

        getBuilder("animated_item_interpolated")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/animated_item_interpolated"));

        getBuilder("animated_item")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/animated_item"));

        getBuilder("test_capa_item")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/test_capa_item"));

        getBuilder("colored_item")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Main.MODID, "item/colored_item"));

        ModelFile propertyItemFull = withExistingParent("property_item_full", "item/generated")
                .texture("layer0", modLoc("item/property_item_full"));

        ModelFile propertyItem = withExistingParent("property_item", "item/generated")
                .texture("layer0", modLoc("item/property_item"))
                .override()
                .predicate(modLoc("test_property"), 1)
                .model(getExistingFile(modLoc("item/property_item_full")))
                .end();

        withExistingParent(ModItems.SEPARATE_PERSPECTIVE.getId().getPath(), "forge:item/default")
                .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(getExistingFile(mcLoc("item/coal"))))
                    .perspective(ItemTransforms.TransformType.GUI, nested().parent(getExistingFile(mcLoc("item/snowball"))))
                    .perspective(ItemTransforms.TransformType.FIXED, nested().parent(getExistingFile(mcLoc("item/diamond"))))
                .end();

    }
}
