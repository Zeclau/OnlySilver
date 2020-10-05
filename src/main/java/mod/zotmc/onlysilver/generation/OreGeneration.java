package mod.zotmc.onlysilver.generation;

import mod.alexndr.simplecorelib.world.OreGenUtils;
import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.init.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

/**
 * Ore generation master-class for OnlySilver.
 */
public class OreGeneration
{
    protected static ConfiguredFeature<?, ?> ORE_SILVER;

    /**
     * Do we care about this biome? Yes, if overworld or nether, no if THEEND. Also
     * init relevant Features, if they are null.
     */
    public static boolean checkAndInitBiome(BiomeLoadingEvent evt)
    {
        if (evt.getCategory() != Biome.Category.THEEND && evt.getCategory() != Biome.Category.NETHER)
        {
            initOverworldFeatures();
            return true;
        }
        return false;
    } // end checkBiome

    /**
     * initialize overworld Features.
     * 
     * @param evt
     */
    protected static void initOverworldFeatures()
    {
        if (ORE_SILVER == null)
        {
            ORE_SILVER = OreGenUtils.buildOverworldOreFeature(
                    ModBlocks.silver_ore.get().getDefaultState(), OnlySilverConfig.silver_cfg); 
        }
    } // end-initOverworldFeatures()

    /**
     * generate overworld ores.
     */
    public static void generateOverworldOres(BiomeLoadingEvent evt)
    {
        evt.getGeneration().withFeature(Decoration.UNDERGROUND_ORES, OreGeneration.ORE_SILVER);
    } // end generateOverworldOres()
    
} // end class OreGeneration
