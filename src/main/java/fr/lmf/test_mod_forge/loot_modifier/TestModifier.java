package fr.lmf.test_mod_forge.loot_modifier;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;

public class TestModifier extends LootModifier {
    public TestModifier(final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected List<ItemStack> doApply(final List<ItemStack> generatedLoot, final LootContext context) {
        final BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
        final BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);

        generatedLoot.add(new ItemStack(Items.COMMAND_BLOCK));

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<TestModifier> {
        @Override
        public TestModifier read(final ResourceLocation location, final JsonObject object, final LootItemCondition[] conditions) {
            return new TestModifier(conditions);
        }

        @Override
        public JsonObject write(final TestModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}