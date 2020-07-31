package mod.zotmc.onlysilver.config;

import java.util.HashMap;
import java.util.Map;

import mod.alexndr.simpleores.api.config.ISimpleConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class OnlySilverConfig implements ISimpleConfig
{
    // recipe flags
    private static Map<String, Boolean> flags = new HashMap<>();
    public static OnlySilverConfig INSTANCE = new OnlySilverConfig();
    
    // general
    public static boolean enableAuraEnchantment = false;
    public static boolean enableIncantationEnchantment = false;
    public static boolean buildSilverGolem = false;
    
    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    public static CountRangeConfig silver_cfg;
    public static int silver_veinsize = 5;

    // client-side
    //public static boolean makeBlockFlame = true;
    
    @Override
    public void clear()
    {
        flags.clear();
    }

    @Override
    public boolean getFlag(String n)
    {
        Boolean obj = flags.get(n);
        return obj != null && obj;
    }

    @Override
    public void putFlag(String n, boolean val)
    {
        flags.put(n, val);
    }

} // end-class
