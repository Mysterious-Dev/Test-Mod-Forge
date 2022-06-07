package fr.lmf.test_mod_forge.items;

import fr.lmf.test_mod_forge.utils.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class TestToolItem extends DiggerItem {

    public TestToolItem(float p_204108_, float p_204109_, Tier p_204110_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, ModTags.TEST_TOOL_TYPE_TAG, p_204112_);
    }
}
