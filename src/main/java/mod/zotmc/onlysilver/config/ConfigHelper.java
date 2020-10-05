package mod.zotmc.onlysilver.config;

import mod.alexndr.simplecorelib.config.ModOreConfig;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper
{
    public static void bakeClient(final ModConfig config) 
    {
        // OnlySilverConfig.makeBlockFlame = ConfigHolder.CLIENT.clientMakeBlockFlame.get();
    } // end bakeClient

    public static void bakeServer(final ModConfig config) 
    {
        // general
        OnlySilverConfig.buildSilverGolem  = ConfigHolder.SERVER.serverBuildSilverGolem.get();
        
        // enchantments
        OnlySilverConfig.enableAuraEnchantment = ConfigHolder.SERVER.serverEnableAuraEnchantment.get();
        OnlySilverConfig.enableIncantationEnchantment = ConfigHolder.SERVER.serverEnableIncantationEnchantment.get();
        
        // Ore generation stuff
        OnlySilverConfig.silver_cfg = new ModOreConfig(
                        new TopSolidRangeConfig(ConfigHolder.SERVER.serverSilverBottomHeight.get(),
                        0, ConfigHolder.SERVER.serverSilverMaxHeight.get()),
                        ConfigHolder.SERVER.serverSilverVeinSize.get(),
                        ConfigHolder.SERVER.serverSilverVeinCount.get());

    } // end bakeServer
} // end-class
