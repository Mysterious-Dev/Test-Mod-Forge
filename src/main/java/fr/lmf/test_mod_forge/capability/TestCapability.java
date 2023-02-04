package fr.lmf.test_mod_forge.capability;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestCapability {
    public static Capability<ITestCapability> POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    public static final ResourceLocation CAP_KEY = new ResourceLocation(Main.MODID, "power");

    @SubscribeEvent
    public static void attachToEntities(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof Player)
        {
            ITestCapability capability;
            if(event.getObject() instanceof ServerPlayer){
                capability = new PlayerTestStorage((ServerPlayer)event.getObject());
            }
            else {
                capability = new TestStorage();
            }

            PlayerTestProvider provider = new PlayerTestProvider(capability);
            event.addCapability(CAP_KEY, provider);
        }
    }

    @SubscribeEvent
    public static void onPlayerSpawn(PlayerEvent.PlayerLoggedInEvent e)
    {
        Player p = e.getEntity();

        p.getCapability(POWER_CAPABILITY).ifPresent(h -> {
            h.setPower(h.getPower());
        });
    }

    @SubscribeEvent
    public static void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent e)
    {
        Player p = e.getEntity();

        p.getCapability(POWER_CAPABILITY).ifPresent(h -> {
            h.setPower(h.getPower());
        });
    }



    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e)
    {
        Player p = e.getEntity();

        p.getCapability(POWER_CAPABILITY).ifPresent(h -> {
            h.setPower(h.getPower());
        });
    }

}
