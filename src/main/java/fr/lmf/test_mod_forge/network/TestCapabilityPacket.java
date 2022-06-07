package fr.lmf.test_mod_forge.network;

import fr.lmf.test_mod_forge.capability.ITestCapability;
import fr.lmf.test_mod_forge.capability.TestCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TestCapabilityPacket {

    private final int power;

    public TestCapabilityPacket(int power) {
        this.power = power;
    }

    public TestCapabilityPacket(ITestCapability instance) {
        this.power = instance.getPower();
    }

    public static void encode(TestCapabilityPacket packet, FriendlyByteBuf buffer){
        buffer.writeInt(packet.power);
    }

    public static TestCapabilityPacket decode(FriendlyByteBuf buffer){
        return new TestCapabilityPacket(buffer.readInt());
    }

    public static void handle(TestCapabilityPacket packet, Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        if(context.getDirection().getReceptionSide() == LogicalSide.CLIENT){
            context.enqueueWork(() -> {
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.getCapability(TestCapability.POWER_CAPABILITY)
                            .ifPresent(capa -> capa.setPower(packet.power));
                }
            });
        }
        context.setPacketHandled(true);
    }

}
