package mod.zotmc.onlysilver.datagen;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.function.Consumer;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.init.ModItems;
import mod.zotmc.onlysilver.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;
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
            registerMiscRecipes(consumer);
            registerToolRecipes(consumer);
            registerArmorRecipes(consumer);
            registerFurnaceRecipes(consumer);
        } // end registerRecipes() 
        
        protected void registerToolRecipes(Consumer<IFinishedRecipe> consumer)
        {
            // axe
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_axe.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .key('T', Tags.Items.RODS_WOODEN)
                .patternLine("SS")
                .patternLine("ST")
                .patternLine(" T")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            // pickaxe
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_pickaxe.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .key('T', Tags.Items.RODS_WOODEN)
                .patternLine("SSS")
                .patternLine(" T ")
                .patternLine(" T ")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
        } // end registerToolRecipes()
        
        protected void registerArmorRecipes(Consumer<IFinishedRecipe> consumer)
        {
        }
        
        protected void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer)
        {
        }
        
        protected void registerMiscRecipes(Consumer<IFinishedRecipe> consumer)
        {
        }
    } // end subclass OnlySilverDataGenerator$Recipes.
    
} // end-class OnlySilverDataGenerator
