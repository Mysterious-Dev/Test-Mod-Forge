package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.init.ModBlocks;
import fr.lmf.test_mod_forge.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TestLangProvider extends LanguageProvider {
    public TestLangProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.TEST_SIMPLE_BLOCK.get(), "Test Simple Block");

        add(ModItems.TEST_PICKAXE.get(), "Test Pickaxe");
        add(ModItems.TEST_TOOL.get(), "Test Tool");

        add(ModItems.ANIMATED_ITEM.get(), "Animated Item");
        add(ModItems.ANIMATED_ITEM_INTERPOLATED.get(), "Animated Item Interpolated");

        add(ModItems.PROPERTY_ITEM.get(), "Property Item");
        add(ModItems.COLORED_ITEM.get(), "Colored Item");

        add(ModItems.CAPA_ITEM.get(), "Test Capa Item");

        add(ModItems.SEPARATE_PERSPECTIVE.get(), "Separate Perspective");

        add("itemGroup.test_tab", "Test Tab");
    }
}
