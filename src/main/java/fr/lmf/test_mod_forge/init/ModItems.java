package fr.lmf.test_mod_forge.init;

import fr.lmf.test_mod_forge.Main;
import fr.lmf.test_mod_forge.items.TestCapaItem;
import fr.lmf.test_mod_forge.items.TestToolItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<Item> TEST_PICKAXE = ITEMS.register("test_pickaxe", ()-> new PickaxeItem(ModTiers.TEST_TIER, 1, -2.8F, new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> TEST_TOOL = ITEMS.register("test_tool", ()-> new TestToolItem( 1, -2.8F, Tiers.DIAMOND, new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> ANIMATED_ITEM_INTERPOLATED = ITEMS.register("animated_item_interpolated", ()-> new Item(new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> ANIMATED_ITEM = ITEMS.register("animated_item", ()-> new Item(new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> PROPERTY_ITEM = ITEMS.register("property_item", ()-> new Item(new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> COLORED_ITEM = ITEMS.register("colored_item", ()-> new Item(new Item.Properties().tab(Main.TEST_TAB)));
    public static final RegistryObject<Item> CAPA_ITEM = ITEMS.register("test_capa_item", ()-> new TestCapaItem(new Item.Properties().tab(Main.TEST_TAB)));

}
