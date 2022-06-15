package fr.lmf.test_mod_forge.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.data.providers.*;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
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
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class ModDataGenerator {

    private static final ResourceKey<PlacedFeature> LARGE_BASALT_COLUMNS_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation("large_basalt_columns"));
    private static final ResourceLocation ADD_BASALT_RL = new ResourceLocation(Main.MODID, "add_basalt");

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event){

        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new TestLangProvider(generator, Main.MODID, "en_us"));
        generator.addProvider(event.includeClient(), new TestItemModelProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new TestBlockstateProvider(generator, Main.MODID, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new TestGlobalLootModifierProvider(generator, Main.MODID));
        generator.addProvider(event.includeServer(), new TestBlockTagProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestBiomeTagsProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new TestRecipeProvider(generator));

        final HolderSet.Named<Biome> badlandsTag = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_BADLANDS);

        final BiomeModifier addBasaltFeature = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                badlandsTag,
                HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, LARGE_BASALT_COLUMNS_KEY.location()))),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION);

        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
                generator, event.getExistingFileHelper(), Main.MODID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, Map.of(
                        ADD_BASALT_RL, addBasaltFeature)));

    }

}
