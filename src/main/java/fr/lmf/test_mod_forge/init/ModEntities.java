package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.entity.TestEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<TestEntity>> TEST_ENTITY =
            ENTITY_TYPES.register("test_entity",
                    () -> EntityType.Builder.of(TestEntity::new, MobCategory.CREATURE)
                            .sized(0.8f, 0.6f)
                            .build(new ResourceLocation(Main.MODID, "test_entity").toString()));

}
