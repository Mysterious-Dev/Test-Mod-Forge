package fr.lmf.test_mod_forge.event;

import fr.lmf.test_mod_forge.capability.TestCapability;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityEvent {

    @SubscribeEvent
    public static void onPlayerTick(BlockEvent.BreakEvent event) {
        event.getPlayer().getCapability(TestCapability.POWER_CAPABILITY).ifPresent(cap -> cap.increasePower(1));
    }

}
