package fr.lmf.test_mod_forge.data.providers;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class TestRecipeProvider extends RecipeProvider {
    public TestRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHERITE_INGOT, 16)
                .pattern("xxx")
                .pattern("yyy")
                .pattern("zzz")
                .define('x', Items.DIAMOND)
                .define('y', Items.IRON_INGOT)
                .define('z', Items.GOLD_INGOT)
                .group("test_recipe")
                .unlockedBy("obtain_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(p_251297_);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DIAMOND)
                .group("test_recipe")
                .requires(Items.DIRT)
                .requires(Items.GOLD_INGOT)
                .unlockedBy("obtain_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
                .save(p_251297_);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_251297_);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_251297_, "iron_ingot_smelting");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_251297_, "iron_ingot_smoking");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.MISC, Items.IRON_INGOT, 10, 100)
                .group("test_recipe")
                .unlockedBy("obtain_netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_251297_, "iron_ingot_blasting");

        LegacyUpgradeRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND_BLOCK), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.BUILDING_BLOCKS, Items.NETHERITE_BLOCK)
                .unlocks("obtain_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(p_251297_, new ResourceLocation("test_smithing_recipe"));
    }
}
