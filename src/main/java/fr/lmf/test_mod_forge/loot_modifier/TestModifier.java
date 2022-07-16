package fr.lmf.test_mod_forge.loot_modifier;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class TestModifier extends LootModifier {

    public static final Supplier<Codec<TestModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("seedItem").forGetter(m -> m.itemToDrop))
            .apply(inst, TestModifier::new)
    ));

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

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}