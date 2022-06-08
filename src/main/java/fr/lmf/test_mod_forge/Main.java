package fr.lmf.test_mod_forge;

import com.mojang.logging.LogUtils;
import fr.lmf.test_mod_forge.capability.TestCapability;
import fr.lmf.test_mod_forge.event.CapabilityEvent;
import fr.lmf.test_mod_forge.init.ModBlocks;
import fr.lmf.test_mod_forge.init.ModItems;
import fr.lmf.test_mod_forge.init.ModLootModifiers;
import fr.lmf.test_mod_forge.init.ModStructures;
import fr.lmf.test_mod_forge.network.NetworkInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "test_mod_forge";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab TEST_TAB = new CreativeModeTab("test_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };

    public Main()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(TestCapability.class);
        MinecraftForge.EVENT_BUS.register(CapabilityEvent.class);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLootModifiers.GLM.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        ModItems.ITEMS.register(bus);

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
}
