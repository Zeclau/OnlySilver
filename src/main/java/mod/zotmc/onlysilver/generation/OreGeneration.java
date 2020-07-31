package mod.zotmc.onlysilver.generation;

import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.init.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Ore generation master-class for OnlySilver.
 */
public class OreGeneration
{

    /**
     * called in setup to generate overworld ores; should respect config entries.
     */
    public static void setupOreGen()
    {
        for (Biome biome : ForgeRegistries.BIOMES.getValues())
        {
            if (biome.getCategory() == Biome.Category.THEEND)
            {
                // We have no ores for the End dimension, so this if statement is just so that
                // we skip biomes in that dimension.
                continue;
            }
            else if (biome.getCategory() == Biome.Category.NETHER)
            {
                // We have no ores for the Nether dimension, so this if statement is just so that
                // we skip biomes in that dimension.
            } // end-else NETHER
            else
            {
                // Overworld
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE
                                .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        ModBlocks.silver_ore.get().getDefaultState(), OnlySilverConfig.silver_veinsize))
                                .withPlacement(Placement.COUNT_RANGE.configure(OnlySilverConfig.silver_cfg)));

            } // end-else all others
        } // end-for Biome
    } // end setupOreGen()

} // end class OreGeneration
