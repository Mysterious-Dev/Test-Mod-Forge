package fr.lmf.test_mod_forge.event;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.tests.BlockTests;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class TestsEvent {

    @SubscribeEvent
    public static void registerTests(RegisterGameTestsEvent event){
        event.register(BlockTests.class);
    }

}
