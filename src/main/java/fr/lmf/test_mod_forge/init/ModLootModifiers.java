package fr.lmf.test_mod_forge.init;

import com.mojang.serialization.Codec;
import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.loot_modifier.TestModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Main.MODID);

    public static final RegistryObject<Codec<TestModifier>> TEST_MODIFIER = GLM.register("test_modifier", TestModifier.CODEC);

}
