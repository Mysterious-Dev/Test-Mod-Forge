package fr.lmf.test_mod_forge.data.providers;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

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

        ShapelessRecipeBuilder.shapeless(Items.DIAMOND)
                .group("test_recipe")
                .requires(Items.DIRT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("obtain_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
                .save(p_176532_);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Items.NETHERITE_INGOT), Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_176532_);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.NETHERITE_INGOT), Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_176532_, "iron_ingot_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Items.NETHERITE_INGOT), Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_176532_, "iron_ingot_smoking");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.NETHERITE_INGOT), Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_176532_, "iron_ingot_blasting");

        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND_BLOCK), Ingredient.of(Items.NETHERITE_INGOT), Items.NETHERITE_BLOCK)
                .unlocks("obtain_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(p_176532_, new ResourceLocation("test_smithing_recipe"));

    }
}
