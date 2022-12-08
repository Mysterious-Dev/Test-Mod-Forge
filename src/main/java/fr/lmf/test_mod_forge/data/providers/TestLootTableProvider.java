package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.data.providers.loots.TestBlockLoots;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class TestLootTableProvider extends LootTableProvider {

    public TestLootTableProvider(PackOutput p_254123_, Set<ResourceLocation> p_254481_) {
        super(p_254123_, p_254481_, create(p_254123_).getTables());
    }

    public static LootTableProvider create(PackOutput p_250807_) {
        return new LootTableProvider(p_250807_, BuiltInLootTables.all(), List.of(new LootTableProvider.SubProviderEntry(VanillaBlockLoot::new, LootContextParamSets.BLOCK)));
    }
}
