package fr.lmf.test_mod_forge.utils;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import fr.lmf.test_mod_forge.Main;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class ModPools {

    public static final Holder<StructureTemplatePool> START = Pools.register(new StructureTemplatePool(new ResourceLocation("oak_abandonned_house"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy(Main.MODID + ":oak_abandonned_house"), 1)), StructureTemplatePool.Projection.RIGID));

    static {

    }

}
