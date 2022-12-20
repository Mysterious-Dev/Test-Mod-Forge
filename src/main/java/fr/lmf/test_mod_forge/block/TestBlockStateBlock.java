package fr.lmf.test_mod_forge.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TestBlockStateBlock extends Block {

    public static final BooleanProperty TEST_PROPERTY = BooleanProperty.create("test_property");

    public TestBlockStateBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(TEST_PROPERTY, Boolean.valueOf(true)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(TEST_PROPERTY);
    }
}
