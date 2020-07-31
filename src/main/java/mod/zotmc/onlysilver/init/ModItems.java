package mod.zotmc.onlysilver.init;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.content.OnlySilverArmorMaterial;
import mod.zotmc.onlysilver.content.OnlySilverItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link Item}s.
 * Suppliers that create Items are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the Item Registry Event is fired by Forge and it is time for the mod to
 * register its Items, our Items are created and registered by the DeferredRegister.
 * The Item Registry Event will always be called after the Block registry is filled.
 * Note: This supports registry overrides.
 *
 * @author Sinhika, notes by Cadiboo.
 */
public final class ModItems 
{
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, OnlySilver.MODID);

    // ingots and nuggets
    public static final RegistryObject<Item> silver_ingot = ITEMS.register("silver_ingot", 
            ()-> new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> silver_nugget = ITEMS.register("silver_nugget",
            ()-> new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // parts

   
    // TOOLS & WEAPONS
    // bows

    // swords
    public static final RegistryObject<SwordItem> silver_sword = ITEMS.register("silver_sword",
            () -> new SwordItem(OnlySilverItemTier.SILVER, 3, -2.4F,
                                new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // pickaxes
    public static final RegistryObject<PickaxeItem> silver_pickaxe = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(OnlySilverItemTier.SILVER, 1, -2.8F,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // axes
    public static final RegistryObject<AxeItem> silver_axe = ITEMS.register("silver_axe",
            () -> new AxeItem(OnlySilverItemTier.SILVER, 7.0F, -3.1F,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // shovels
    public static final RegistryObject<ShovelItem> silver_shovel = ITEMS.register("silver_shovel",
            () -> new ShovelItem(OnlySilverItemTier.SILVER, 1.5F, -3.0F,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // hoes
    public static final RegistryObject<HoeItem> silver_hoe = ITEMS.register("silver_hoe",
            () -> new HoeItem(OnlySilverItemTier.SILVER,-2.0F,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));

    // ARMOR
    // silver
    public static final RegistryObject<ArmorItem> silver_helmet = ITEMS.register("silver_helmet",
            () -> new ArmorItem(OnlySilverArmorMaterial.SILVER, EquipmentSlotType.HEAD,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<ArmorItem> silver_leggings = ITEMS.register("silver_leggings",
            () -> new ArmorItem(OnlySilverArmorMaterial.SILVER, EquipmentSlotType.LEGS,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<ArmorItem> silver_chestplate = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(OnlySilverArmorMaterial.SILVER, EquipmentSlotType.CHEST,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));
    public static final RegistryObject<ArmorItem> silver_boots = ITEMS.register("silver_boots",
            () -> new ArmorItem(OnlySilverArmorMaterial.SILVER, EquipmentSlotType.FEET,
                    new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)));


} // end class
