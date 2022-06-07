package fr.lmf.test_mod_forge.capability;

import fr.lmf.test_mod_forge.network.NetworkInit;
import fr.lmf.test_mod_forge.network.TestCapabilityPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class PlayerTestStorage extends TestStorage {

    private final ServerPlayer player;

    public PlayerTestStorage(ServerPlayer player) {
        this.player = player;
    }

    @Override
    public void setPower(int value) {
        super.setPower(value);

        if (player.connection != null) {
            player.getCapability(TestCapability.POWER_CAPABILITY).ifPresent(capability -> NetworkInit.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> this.player), new TestCapabilityPacket(capability)
            ));
        }
    }
}
