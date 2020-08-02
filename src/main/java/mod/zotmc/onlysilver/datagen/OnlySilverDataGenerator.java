package mod.zotmc.onlysilver.datagen;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.function.Consumer;

import mod.zotmc.onlysilver.OnlySilver;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * bundles up the GatherDataEvent handler and all the necessary data providers for
 * data generation.
 * @author Sinhika
 */
@EventBusSubscriber(modid = OnlySilver.MODID, bus = MOD)
public class OnlySilverDataGenerator
{

    /**
     * GatherDataEvent handler.
     * @param event the GatherDataEvent.
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        if (event.includeServer())
        {
            gen.addProvider(new Recipes(gen));
        }
     } // end gatherData()

    /** 
     * RecipeProvider for OnlySilver.
     * @author Sinhika
     *
     */
    public static class Recipes extends RecipeProvider implements IConditionBuilder
    {

        public Recipes(DataGenerator generatorIn)
        {
            super(generatorIn);
        }

        protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
        {
        } // end registerRecipes() 
        
    } // end subclass OnlySilverDataGenerator$Recipes.
    
} // end-class OnlySilverDataGenerator
