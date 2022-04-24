package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.init.ModLootModifiers;
import fr.lmf.test_mod_forge.loot_modifier.TestModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class TestGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public TestGlobalLootModifierProvider(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    @Override
    protected void start() {
        add("spawner_drops", ModLootModifiers.TEST_MODIFIER.get(), new TestModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(BuiltInLootTables.BASTION_TREASURE).build()
                }
        ));
    }
}
