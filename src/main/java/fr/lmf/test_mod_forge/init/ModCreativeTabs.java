package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MODID);

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(ModBlocks.TEST_SIMPLE_BLOCK.get()))
            .title(Component.translatable("itemGroup.test_tab"))
            .withLabelColor(0x0000FF)
            .displayItems((parameters, output) -> {
                output.accept(new ItemStack(ModBlocks.TEST_SIMPLE_BLOCK.get()));
                output.accept(new ItemStack(ModBlocks.TEST_BLOCKSTATE_BLOCK.get()));
                //output.accept(new ItemStack(ModBlocks.TEST_VOXEL_SHAPE_BLOCK.get()));
                output.accept(new ItemStack(ModItems.CAPA_ITEM.get()));
                output.accept(new ItemStack(ModItems.ANIMATED_ITEM.get()));
                if(parameters.hasPermissions()){
                    output.accept(new ItemStack(ModItems.TEST_NBT.get()));
                }
                output.accept(new ItemStack(ModItems.ANIMATED_ITEM_INTERPOLATED.get()));
                output.accept(new ItemStack(ModItems.SEPARATE_PERSPECTIVE.get()));
                output.accept(new ItemStack(ModItems.TEST_PICKAXE.get()));
                output.accept(new ItemStack(ModItems.COLORED_ITEM.get()));
                output.accept(new ItemStack(ModItems.PROPERTY_ITEM.get()));
                output.accept(new ItemStack(ModItems.TEST_TOOL.get()));
            }).build());

}
