package fr.lmf.test_mod_forge.event;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.capability.TestCapability;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEvent {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        event.getPlayer().getCapability(TestCapability.POWER_CAPABILITY).ifPresent(cap -> cap.increasePower(1));
    }

}
