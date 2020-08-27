package mod.zotmc.onlysilver.config;

import net.minecraft.world.gen.placement.CountRangeConfig;

public class OnlySilverConfig
{
    // recipe flags
    public static OnlySilverConfig INSTANCE = new OnlySilverConfig();
    
    // general
    public static boolean enableAuraEnchantment = true;
    public static boolean enableIncantationEnchantment = true;
    public static boolean buildSilverGolem = true;
    
    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    public static CountRangeConfig silver_cfg;
    public static int silver_veinsize = 5;

} // end-class
