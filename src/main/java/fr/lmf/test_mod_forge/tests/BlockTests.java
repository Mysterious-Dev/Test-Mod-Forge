package fr.lmf.test_mod_forge.tests;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(Main.MODID)
public class BlockTests {

    @GameTest
    public static void explosionTest(GameTestHelper helper){

        helper.spawn(EntityType.TNT, new BlockPos(2,4,2));

        helper.succeedWhenBlockPresent(Blocks.AIR, new BlockPos(2,3,2));

    }

}
