package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.loot_modifier.TestModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Main.MODID);

    public static final RegistryObject<TestModifier.Serializer> TEST_MODIFIER = GLM.register("test_modifier", TestModifier.Serializer::new);

}
