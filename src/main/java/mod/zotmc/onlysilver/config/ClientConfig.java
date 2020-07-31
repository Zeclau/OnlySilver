package mod.zotmc.onlysilver.config;

import mod.zotmc.onlysilver.OnlySilver;
import net.minecraftforge.common.ForgeConfigSpec;

public final class ClientConfig
{
    final ForgeConfigSpec.BooleanValue clientMakeBlockFlame;
    
    ClientConfig(final ForgeConfigSpec.Builder builder) 
    {
        builder.push("General");
        clientMakeBlockFlame = builder.comment("Hadite Block emits flames")
                .translation(OnlySilver.MODID + "config.MakeBlockFlame")
                .define("MakeBlockFlame", true);
        builder.pop();
    }
} // end-class

