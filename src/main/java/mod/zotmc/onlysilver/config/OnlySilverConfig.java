package mod.zotmc.onlysilver.config;

import mod.alexndr.simplecorelib.config.ModOreConfig;
import mod.alexndr.simplecorelib.config.SimpleConfig;

public class OnlySilverConfig extends SimpleConfig
{
    // recipe flags
    public static OnlySilverConfig INSTANCE = new OnlySilverConfig();
    
    // general
    public static boolean enableAuraEnchantment = true;
    public static boolean enableIncantationEnchantment = true;
    public static boolean buildSilverGolem = true;
    public static boolean addModLootToChests = true;
    public static boolean enableSilverOre = true;
    
    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    public static ModOreConfig silver_cfg;


} // end-class
