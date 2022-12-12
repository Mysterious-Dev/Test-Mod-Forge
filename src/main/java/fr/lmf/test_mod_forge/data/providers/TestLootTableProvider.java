package fr.lmf.test_mod_forge.data.providers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.data.providers.loots.TestBlockLoots;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TestLootTableProvider extends LootTableProvider {
    private TestLootTableProvider(final PackOutput output, final List<SubProviderEntry> subProviders) {
        super(output, Set.of(), subProviders);
    }

    public static TestLootTableProvider create(final PackOutput output) {
        return new TestLootTableProvider(output, ImmutableList.of(
                new SubProviderEntry(TestBlockLoots::new, LootContextParamSets.BLOCK)
        ));
    }

    @Override
    protected void validate(final Map<ResourceLocation, LootTable> map, final ValidationContext validationContext) {
        map.forEach((id, table) -> LootTables.validate(validationContext, id, table));
    }
}