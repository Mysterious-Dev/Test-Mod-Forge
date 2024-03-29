package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.loot_modifier.TestModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class TestGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public TestGlobalLootModifierProvider(PackOutput packOutput, String modid) {
        super(packOutput, modid);
    }

    @Override
    protected void start() {
        add("cobblestone_ore_drop", new TestModifier(
                    new LootItemCondition[]{
                    },
                    4,
                    Items.COBBLESTONE
                )
        );
    }
}


