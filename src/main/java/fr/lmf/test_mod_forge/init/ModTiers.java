package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.utils.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModTiers {

    public static final Tier TEST_TIER = TierSortingRegistry.registerTier(
            new ForgeTier(5, 5000, 10, 100, 0, ModTags.MON_TIER_TAG, () -> Ingredient.of(Items.OBSIDIAN)),
            new ResourceLocation(Main.MODID, "test_tier"),
            List.of(Tiers.DIAMOND), List.of());

}
