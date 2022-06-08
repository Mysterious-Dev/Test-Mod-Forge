package fr.lmf.test_mod_forge.loot_modifier;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class TestModifier extends LootModifier {

    private final Item itemToDrop;
    public TestModifier(final LootItemCondition[] conditionsIn, Item itemDrop) {
        super(conditionsIn);
        itemToDrop = itemDrop;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        final BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);

        if (state != null && state.getBlock() instanceof DropExperienceBlock){
            generatedLoot.add(new ItemStack(Items.COBBLESTONE));
        }

        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<TestModifier> {
        @Override
        public TestModifier read(final ResourceLocation location, final JsonObject object, final LootItemCondition[] conditions) {
            Item itemDrop = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item")));
            return new TestModifier(conditions, itemDrop);
        }

        @Override
        public JsonObject write(final TestModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.itemToDrop).toString());
            return json;
        }
    }
}