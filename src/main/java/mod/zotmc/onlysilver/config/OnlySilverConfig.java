package mod.zotmc.onlysilver.config;

import java.util.HashMap;
import java.util.Map;

import mod.alexndr.simplecorelib.config.ISimpleConfig;
import mod.alexndr.simplecorelib.config.ModOreConfig;

public class OnlySilverConfig implements ISimpleConfig
{
    private static Map<String, Boolean> flags = new HashMap<>();

    // recipe flags
    public static OnlySilverConfig INSTANCE = new OnlySilverConfig();
    
    // general
    public static boolean enableAuraEnchantment = true;
    public static boolean enableIncantationEnchantment = true;
    public static boolean buildSilverGolem = true;
    
    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    public static ModOreConfig silver_cfg;

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
