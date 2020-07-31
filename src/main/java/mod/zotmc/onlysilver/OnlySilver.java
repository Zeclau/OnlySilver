package mod.zotmc.onlysilver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.zotmc.onlysilver.config.ConfigHolder;
import mod.zotmc.onlysilver.init.ModBlocks;
import mod.zotmc.onlysilver.init.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OnlySilver.MODID)
public class OnlySilver
{
    // modid 
    public static final String MODID = "simplemod";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public OnlySilver()
    {
        LOGGER.info("Hello from Only Silver!");
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        
        // Register Configs
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
    } // end SimpleOres()

} // end class SimpleOres
