package fr.lmf.test_mod_forge.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TestNbtItem extends Item {
    public TestNbtItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item.getOrCreateTag().putString("test_nbt", "Test Value");
        return true;
    }
}
