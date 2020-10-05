package mod.zotmc.onlysilver.config;

import mod.alexndr.simplecorelib.config.ModOreConfig;

public class OnlySilverConfig
{
    // recipe flags
    public static OnlySilverConfig INSTANCE = new OnlySilverConfig();
    
    // general
    public static boolean enableAuraEnchantment = true;
    public static boolean enableIncantationEnchantment = true;
    public static boolean buildSilverGolem = true;
    
    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    public static ModOreConfig silver_cfg;

} // end-class
