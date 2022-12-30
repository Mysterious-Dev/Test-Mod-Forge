package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class TestAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator{
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement.Builder.advancement().display(Items.DIRT,
                        Component.translatable(Items.DIRT.getDescriptionId()),
                        Component.translatable("dirt_description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIRT))
                .save(saver, new ResourceLocation(Main.MODID, "obtain_dirt"), existingFileHelper);
    }
}
