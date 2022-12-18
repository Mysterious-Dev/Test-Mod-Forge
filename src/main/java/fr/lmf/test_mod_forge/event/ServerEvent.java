package fr.lmf.test_mod_forge.event;

import com.mojang.brigadier.arguments.StringArgumentType;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.commands.TestCommand;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.EnumArgument;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvent {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event)
    {
        TestCommand.register(event.getDispatcher(), event.getBuildContext());
    }

}
