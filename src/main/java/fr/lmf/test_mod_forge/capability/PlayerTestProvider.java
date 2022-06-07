package fr.lmf.test_mod_forge.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerTestProvider implements ICapabilitySerializable<IntTag> {
    private ITestCapability holder;
    private final LazyOptional<ITestCapability> lazyOptional = LazyOptional.of(() -> this.holder);

    public PlayerTestProvider(ITestCapability holder) {
        this.holder = holder;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return TestCapability.POWER_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public IntTag serializeNBT() {
        return IntTag.valueOf(this.holder.getPower());
    }

    @Override
    public void deserializeNBT(IntTag nbt) {
        if (holder == null)
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        holder.setPower(nbt.getAsInt());
    }

}
