package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.utils.ModPools;
import fr.lmf.test_mod_forge.utils.ModTags;
import fr.lmf.test_mod_forge.world.feature.AbandonnedHouseFeature;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Main.MODID);
    public static final DeferredRegister<ConfiguredStructureFeature<?, ?>> CONFIGURED_STRUCTURES = DeferredRegister.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, Main.MODID);

    public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.create(Registry.STRUCTURE_SET_REGISTRY, Main.MODID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> ABANDONNED_HOUSE = STRUCTURES.register("abandonned_house", ()-> new AbandonnedHouseFeature(JigsawConfiguration.CODEC));

    public static final RegistryObject<ConfiguredStructureFeature<?, ?>> CONFIGURED_ABANDONNED_HOUSE = CONFIGURED_STRUCTURES.register("abandonned_house", ()-> ABANDONNED_HOUSE.get().configured(new JigsawConfiguration(ModPools.START, 6), ModTags.HAS_ABANDONNED_HOUSE, true));
    public static final RegistryObject<StructureSet> SET_ABANDONNED_HOUSE = STRUCTURE_SETS.register("abandonned_house", ()-> new StructureSet(CONFIGURED_ABANDONNED_HOUSE.getHolder().get(), new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 456415678)));

}
