package fr.lmf.test_mod_forge.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.data.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event){

        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new TestLangProvider(generator, Main.MODID, "en_us"));
        generator.addProvider(event.includeClient(), new TestItemModelProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new TestBlockstateProvider(generator, Main.MODID, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new TestGlobalLootModifierProvider(generator, Main.MODID));
        generator.addProvider(event.includeServer(), new TestBlockTagProvider(generator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestBiomeTagsProvider(generator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        /*TODO Corriger le générateur pour les loot tables*/
        generator.addProvider(event.includeServer(), TestLootTableProvider.create(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TestRecipeProvider(generator.getPackOutput()));

    }

}
