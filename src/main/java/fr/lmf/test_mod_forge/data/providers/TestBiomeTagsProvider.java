package fr.lmf.test_mod_forge.data.providers;

import fr.lmf.test_mod_forge.utils.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class TestBiomeTagsProvider extends BiomeTagsProvider {
    public TestBiomeTagsProvider(DataGenerator p_211094_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_211094_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModTags.HAS_ABANDONNED_HOUSE)
                .add(Biomes.PLAINS);
    }
}
