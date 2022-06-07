package fr.lmf.test_mod_forge.items;

import fr.lmf.test_mod_forge.capability.TestCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestCapaItem extends Item {
    public TestCapaItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        if(Minecraft.getInstance().player != null)
            Minecraft.getInstance().player.getCapability(TestCapability.POWER_CAPABILITY).ifPresent(cap -> p_41423_.add(new TextComponent("Votre Power : " + cap.getPower())));
    }
}
