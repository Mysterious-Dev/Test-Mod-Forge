package fr.lmf.test_mod_forge.entity.client;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.entity.TestEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TestEntityRenderer extends MobRenderer<TestEntity, TestEntityModel<TestEntity>> {
    private static final ResourceLocation COW_LOCATION = new ResourceLocation(Main.MODID, "textures/entity/test.png");

    public TestEntityRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new TestEntityModel<>(p_173956_.bakeLayer(TestEntityModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(TestEntity p_114029_) {
        return COW_LOCATION;
    }
}
