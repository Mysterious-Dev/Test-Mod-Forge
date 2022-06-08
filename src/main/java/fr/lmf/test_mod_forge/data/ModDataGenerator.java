package fr.lmf.test_mod_forge.data;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.data.providers.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Main.MODID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event){

        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeClient(), new TestLangProvider(generator, Main.MODID, "en_us"));
        generator.addProvider(event.includeClient(), new TestItemModelProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new TestBlockstateProvider(generator, Main.MODID, event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(), new TestGlobalLootModifierProvider(generator, Main.MODID));
        generator.addProvider(event.includeServer(), new TestBlockTagProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestBiomeTagsProvider(generator, Main.MODID, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new TestLootTableProvider(generator));
        generator.addProvider(event.includeServer(), new TestRecipeProvider(generator));


    }

}
