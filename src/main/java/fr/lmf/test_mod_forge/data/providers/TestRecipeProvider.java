package fr.lmf.test_mod_forge.data.providers;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class TestRecipeProvider extends RecipeProvider {
    public TestRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {

        ShapedRecipeBuilder.shaped(Items.NETHERITE_INGOT, 16)
                .pattern("xxx")
                .pattern("yyy")
                .pattern("zzz")
                .define('x', Items.DIAMOND)
                .define('y', Items.IRON_INGOT)
                .define('z', Items.GOLD_INGOT)
                .group("test_recipe")
                .unlockedBy("obtain_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(p_176532_);

    }
}
