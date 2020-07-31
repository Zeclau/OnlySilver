package mod.zotmc.onlysilver.config;

import mod.zotmc.onlysilver.OnlySilver;
import net.minecraftforge.common.ForgeConfigSpec;

public final class ServerConfig
{
    // ore generation
    final ForgeConfigSpec.IntValue serverSilverVeinSize;
    final ForgeConfigSpec.IntValue serverSilverVeinCount;
    final ForgeConfigSpec.IntValue serverSilverBottomHeight;
    final ForgeConfigSpec.IntValue serverSilverMaxHeight;

    ServerConfig(final ForgeConfigSpec.Builder builder)
    {
        builder.push("Ore Generation");
        builder.push("Silver");
        serverSilverVeinSize = builder.comment("Silver ore vein size")
                .translation(OnlySilver.MODID + "config.SilverVeinSize")
                .defineInRange("SilverVeinSize",  17, 0, Integer.MAX_VALUE);
        serverSilverVeinCount = builder.comment("Silver ore vein count per chunk")
                .translation(OnlySilver.MODID + "config.SilverVeinCount")
                .defineInRange("SilverVeinCount",  9, 0, Integer.MAX_VALUE);
        serverSilverBottomHeight = builder
                .comment("Silver ore minimum height")
                .translation(OnlySilver.MODID + ".config.serverSilverBottomHeight")
                .defineInRange("SilverBottomHeight", 0, 1, 127);
        serverSilverMaxHeight = builder
                .comment("Silver ore maximum height")
                .translation(OnlySilver.MODID + ".config.serverSilverMaxHeight")
                .defineInRange("SilverMaxHeight", 128, 1, 128);
        builder.pop();
        builder.pop();
    } // end ctor

} // end-class
