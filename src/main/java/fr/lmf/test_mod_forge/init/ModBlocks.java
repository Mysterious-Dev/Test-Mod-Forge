package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.block.TestVoxelShapeBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Block> TEST_SIMPLE_BLOCK = registerBlock("test_simple_block", ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f, 10f).requiresCorrectToolForDrops()), new Item.Properties());
    //public static final RegistryObject<Block> TEST_VOXEL_SHAPE_BLOCK = registerBlock("test_voxel_shape_block", ()-> new TestVoxelShapeBlock(BlockBehaviour.Properties.of(Material.STONE).strength(10f, 10f).requiresCorrectToolForDrops()), new Item.Properties());

    private static RegistryObject<net.minecraft.world.level.block.Block> registerBlock(final String name, final Supplier<Block> blockFactory, final Item.Properties properties) {
        final RegistryObject<Block> block = BLOCKS.register(name, blockFactory);

        BLOCK_ITEMS.register(name, ()-> new BlockItem(block.get(), properties));

        return block;
    }

}
