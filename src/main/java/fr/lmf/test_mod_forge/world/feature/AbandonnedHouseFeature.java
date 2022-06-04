package fr.lmf.test_mod_forge.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;

public class AbandonnedHouseFeature extends JigsawFeature {
    public AbandonnedHouseFeature(Codec<JigsawConfiguration> p_67333_) {
        super(p_67333_, 0, true, true, (p_197185_) -> {
            return true;
        });
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
