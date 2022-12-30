package fr.lmf.test_mod_forge.data;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.data.providers.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DataPackRegistriesHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event){

        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new TestLangProvider(generator.getPackOutput(), Main.MODID, "en_us"));
        generator.addProvider(event.includeClient(), new TestItemModelProvider(generator.getPackOutput(), Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new TestBlockstateProvider(generator.getPackOutput(), Main.MODID, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new TestGlobalLootModifierProvider(generator.getPackOutput(), Main.MODID));
        generator.addProvider(event.includeServer(), new TestBlockTagProvider(generator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestBiomeTagsProvider(generator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), TestLootTableProvider.create(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TestRecipeProvider(generator.getPackOutput()));

        generator.addProvider(true, TestPackMetadataProvider.create(generator.getPackOutput()));

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(generator.getPackOutput(), event.getLookupProvider(), createLookup(), Set.of(Main.MODID)));

    }

    private static RegistrySetBuilder createLookup() {
        final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                .add(Registries.BIOME, ModDataGenerator::bootstrap);
        return BUILDER;
    }

    public static void bootstrap(final BootstapContext<Biome> context) {
        final var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        final var configuredWorldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        // TODO: Figure out SurfaceRules to replace SurfaceBuilders
        context.register(ResourceKey.create(Registries.BIOME, new ResourceLocation(Main.MODID, "test_desert")), OverworldBiomes.desert(placedFeatures, configuredWorldCarvers));
    }

}
