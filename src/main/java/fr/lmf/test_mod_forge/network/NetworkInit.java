package fr.lmf.test_mod_forge.network;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkInit {
    public static final String NETWORK_VERSION = "1.0.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "network"),
            () -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION),
            version -> version.equals(NETWORK_VERSION));

    public static void init() {
        CHANNEL.registerMessage(0, TestCapabilityPacket.class, TestCapabilityPacket::encode, TestCapabilityPacket::decode, TestCapabilityPacket::handle);
    }

}
