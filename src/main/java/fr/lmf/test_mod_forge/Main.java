package fr.lmf.test_mod_forge;

import com.mojang.logging.LogUtils;
import fr.lmf.test_mod_forge.entity.client.TestEntityRenderer;
import fr.lmf.test_mod_forge.init.*;
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
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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
        
        bus.addListener(this::onCreativeModeTabBuildContents);

        ModLootModifiers.GLM.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        ModItems.ITEMS.register(bus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(bus);

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

    public void onCreativeModeTabBuildContents(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS)
            event.accept(Blocks.REDSTONE_BLOCK);
    }
}
