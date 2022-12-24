package fr.lmf.test_mod_forge.data.providers;

import com.google.common.collect.ImmutableMap;
import com.mojang.bridge.game.PackType;
import net.minecraft.DetectedVersion;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;

import static net.minecraft.server.packs.PackType.CLIENT_RESOURCES;
import static net.minecraft.server.packs.PackType.SERVER_DATA;

public class TestPackMetadataProvider {
    public static PackMetadataGenerator create(final PackOutput output) {
        return new PackMetadataGenerator(output)
                .add(
                        PackMetadataSection.TYPE,
                        new PackMetadataSection(Component.translatable("pack.test_mod_forge.description"),
                                DetectedVersion.BUILT_IN.getPackVersion(PackType.RESOURCE),
                                ImmutableMap.<net.minecraft.server.packs.PackType, Integer>builder()
                                        .put(SERVER_DATA, DetectedVersion.BUILT_IN.getPackVersion(PackType.DATA))
                                        .put(CLIENT_RESOURCES, DetectedVersion.BUILT_IN.getPackVersion(PackType.RESOURCE))
                                        .build()
                        )
                );
    }
}
