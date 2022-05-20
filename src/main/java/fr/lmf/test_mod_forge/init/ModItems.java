package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> TEST_PICKAXE = ITEMS.register("test_pixkaxe", ()-> new PickaxeItem(ModTiers.TEST_TIER, 1, -2.8F, new Item.Properties()));

}
