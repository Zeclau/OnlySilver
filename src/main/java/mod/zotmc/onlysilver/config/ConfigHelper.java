package mod.zotmc.onlysilver.config;

import net.minecraft.world.gen.placement.CountRangeConfig;
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
        OnlySilverConfig.silver_veinsize = ConfigHolder.SERVER.serverSilverVeinSize.get();
        OnlySilverConfig.silver_cfg = 
                new CountRangeConfig(ConfigHolder.SERVER.serverSilverVeinCount.get(),
                        ConfigHolder.SERVER.serverSilverBottomHeight.get(),
                        0, ConfigHolder.SERVER.serverSilverMaxHeight.get());

    } // end bakeServer
} // end-class
