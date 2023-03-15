package fr.lmf.test_mod_forge;

import com.mojang.logging.LogUtils;
import fr.lmf.test_mod_forge.entity.client.TestEntityRenderer;
import fr.lmf.test_mod_forge.init.ModBlocks;
import fr.lmf.test_mod_forge.init.ModEntities;
import fr.lmf.test_mod_forge.init.ModItems;
import fr.lmf.test_mod_forge.init.ModLootModifiers;
import fr.lmf.test_mod_forge.network.NetworkInit;
import fr.lmf.test_mod_forge.utils.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "test_mod_forge";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        final String configFolder = "test_mod_forge/";

        modLoadingContext.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ModConfig.clientSpec, configFolder + "client.toml");
        modLoadingContext.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, ModConfig.serverSpec, configFolder + "server.toml");
        modLoadingContext.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.commonSpec, configFolder + "common.toml");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCreativeModeTabRegister);
        bus.addListener(this::onCreativeModeTabBuildContents);

        ModLootModifiers.GLM.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        ModItems.ITEMS.register(bus);

        ModEntities.ENTITY_TYPES.register(bus);

        /*ModStructures.STRUCTURES.register(bus);
        ModStructures.CONFIGURED_STRUCTURES.register(bus);
        ModStructures.STRUCTURE_SETS.register(bus);*/
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ItemProperties.register(ModItems.PROPERTY_ITEM.get(), new ResourceLocation(Main.MODID, "test_property"), (stack, level, livingEntity, id) -> {
            return stack.getOrCreateTag().getBoolean("fill") ? 1.0f : 0.0f;
        });

        Minecraft.getInstance().getItemColors().register((stack, color) -> {
            return stack.getOrCreateTag().contains("color") ? stack.getOrCreateTag().getInt("color") : 0x13421772;
        }, ModItems.COLORED_ITEM.get());

        EntityRenderers.register(ModEntities.TEST_ENTITY.get(), TestEntityRenderer::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        NetworkInit.init();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    private void onCreativeModeTabRegister(CreativeModeTabEvent.Register event)
    {


        CreativeModeTab TEST_TAB = event.registerCreativeModeTab(new ResourceLocation(MODID, "test_tab"), List.of(), List.of(CreativeModeTabs.SPAWN_EGGS), builder -> builder
                .icon(() -> new ItemStack(ModBlocks.TEST_SIMPLE_BLOCK.get()))
                .title(Component.translatable("itemGroup.test_tab"))
                .withLabelColor(0x0000FF)
                .displayItems((displayParameters, output) -> {
                    output.accept(new ItemStack(ModBlocks.TEST_SIMPLE_BLOCK.get()));
                    output.accept(new ItemStack(ModBlocks.TEST_BLOCKSTATE_BLOCK.get()));
                    //output.accept(new ItemStack(ModBlocks.TEST_VOXEL_SHAPE_BLOCK.get()));
                    output.accept(new ItemStack(ModItems.CAPA_ITEM.get()));
                    output.accept(new ItemStack(ModItems.ANIMATED_ITEM.get()));
                    if(displayParameters.hasPermissions()){
                        output.accept(new ItemStack(ModItems.TEST_NBT.get()));
                    }
                    output.accept(new ItemStack(ModItems.ANIMATED_ITEM_INTERPOLATED.get()));
                    output.accept(new ItemStack(ModItems.SEPARATE_PERSPECTIVE.get()));
                    output.accept(new ItemStack(ModItems.TEST_PICKAXE.get()));
                    output.accept(new ItemStack(ModItems.COLORED_ITEM.get()));
                    output.accept(new ItemStack(ModItems.PROPERTY_ITEM.get()));
                    output.accept(new ItemStack(ModItems.TEST_TOOL.get()));
                }));
    }

    public void onCreativeModeTabBuildContents(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS)
            event.accept(Blocks.REDSTONE_BLOCK);
    }
}
