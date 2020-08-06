package mod.zotmc.onlysilver.datagen;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.function.Consumer;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.init.ModBlocks;
import mod.zotmc.onlysilver.init.ModItems;
import mod.zotmc.onlysilver.init.ModTags;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
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
            registerStorageRecipes(consumer);
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
            
            // hoe
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_hoe.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .key('T', Tags.Items.RODS_WOODEN)
                .patternLine("SS")
                .patternLine(" T")
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
            
            // sword
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_sword.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .key('T', Tags.Items.RODS_WOODEN)
                .patternLine(" S")
                .patternLine(" S")
                .patternLine(" T")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            
            // shovel
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_shovel.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .key('T', Tags.Items.RODS_WOODEN)
                .patternLine(" S")
                .patternLine(" T")
                .patternLine(" T")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            
            // bow
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_bow.get())
                .key('R', ModTags.Items.RODS_SILVER)
                .key('I', Tags.Items.STRING)
                .key('K', Tags.Items.INGOTS_IRON)
                .patternLine(" RI")
                .patternLine("K I")
                .patternLine(" RI")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            
        } // end registerToolRecipes()
        
        protected void registerArmorRecipes(Consumer<IFinishedRecipe> consumer)
        {
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_helmet.get())
                .key('S', ModItems.silver_ingot.get())
                .patternLine("SSS")
                .patternLine("S S")
                .patternLine("   ")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_chestplate.get())
                .key('S', ModItems.silver_ingot.get())
                .patternLine("S S")
                .patternLine("SSS")
                .patternLine("SSS")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_leggings.get())
                .key('S', ModItems.silver_ingot.get())
                .patternLine("SSS")
                .patternLine("S S")
                .patternLine("S S")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_boots.get())
                .key('S', ModItems.silver_ingot.get())
                .patternLine("   ")
                .patternLine("S S")
                .patternLine("S S")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
                
        } // end registerArmorRecipes()
        
        protected void registerStorageRecipes(Consumer<IFinishedRecipe> consumer)
        {
            // block <=> ingots
            ShapelessRecipeBuilder.shapelessRecipe(ModItems.silver_ingot.get(), 9)
                .addIngredient(ModBlocks.silver_block.get())
                .addCriterion("has_item", hasItem(ModTags.Items.BLOCK_SILVER))
                .build(consumer);
            ShapedRecipeBuilder.shapedRecipe(ModBlocks.silver_block.get())
                .key('S', ModItems.silver_ingot.get())
                .patternLine("SSS")
                .patternLine("SSS")
                .patternLine("SSS")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            
            // ingot <=> nuggets
            ShapelessRecipeBuilder.shapelessRecipe(ModItems.silver_nugget.get(), 9)
                .addIngredient(ModItems.silver_ingot.get())
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_ingot.get())
                .key('S', ModItems.silver_nugget.get())
                .patternLine("SSS")
                .patternLine("SSS")
                .patternLine("SSS")
                .addCriterion("has_item", hasItem(ModTags.Items.NUGGETS_SILVER))
                .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_ingot_from_nugget"));
        } // end registerStorageRecipes()
        
        protected void registerMiscRecipes(Consumer<IFinishedRecipe> consumer)
        {
            ShapedRecipeBuilder.shapedRecipe(ModItems.silver_rod.get())
                .key('S', ModTags.Items.INGOTS_SILVER)
                .patternLine("S  ")
                .patternLine("S  ")
                .addCriterion("has_item", hasItem(ModTags.Items.INGOTS_SILVER))
                .build(consumer);
        } // end registerMiscRecipes()

        protected void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer)
        {
            // ore => ingots
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ModBlocks.silver_ore.get()), 
                                                ModItems.silver_ingot.get(), 0.8F, 200)
                .addCriterion("has_item", hasItem(ModBlocks.silver_ore.get()))
                .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_ingot_from_smelting"));
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ModBlocks.silver_ore.get()), 
                                                ModItems.silver_ingot.get(), 0.8F, 100)
                    .addCriterion("has_item", hasItem(ModBlocks.silver_ore.get()))
                    .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_ingot_from_blasting"));
            
            // vanilla recycling, tools/armor => nuggets
            // smelting
            CookingRecipeBuilder
                    .smeltingRecipe(
                            Ingredient.fromItems(ModItems.silver_axe.get(), ModItems.silver_bow.get(),
                                    ModItems.silver_hoe.get(), ModItems.silver_pickaxe.get(), ModItems.silver_rod.get(),
                                    ModItems.silver_shovel.get(), ModItems.silver_sword.get()),
                            ModItems.silver_nugget.get(), 0.4F, 200)
                    .addCriterion("has_item", hasItem(ModItems.silver_axe.get()))
                    .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_nugget_from_smelting_tools"));

            CookingRecipeBuilder
                    .smeltingRecipe(
                            Ingredient.fromItems(ModItems.silver_boots.get(), ModItems.silver_chestplate.get(),
                                    ModItems.silver_helmet.get(), ModItems.silver_leggings.get()),
                            ModItems.silver_nugget.get(), 0.4F, 200)
                    .addCriterion("has_item", hasItem(ModItems.silver_boots.get()))
                    .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_nugget_from_smelting_armor"));
          
            // blasting
            CookingRecipeBuilder
                    .blastingRecipe(
                            Ingredient.fromItems(ModItems.silver_axe.get(), ModItems.silver_bow.get(),
                                    ModItems.silver_hoe.get(), ModItems.silver_pickaxe.get(), ModItems.silver_rod.get(),
                                    ModItems.silver_shovel.get(), ModItems.silver_sword.get()),
                            ModItems.silver_nugget.get(), 0.4F, 100)
                    .addCriterion("has_item", hasItem(ModItems.silver_axe.get()))
                    .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_nugget_from_blasting_tools"));

            CookingRecipeBuilder
                    .blastingRecipe(
                            Ingredient.fromItems(ModItems.silver_boots.get(), ModItems.silver_chestplate.get(),
                                    ModItems.silver_helmet.get(), ModItems.silver_leggings.get()),
                            ModItems.silver_nugget.get(), 0.4F, 100)
                    .addCriterion("has_item", hasItem(ModItems.silver_boots.get()))
                    .build(consumer, new ResourceLocation(OnlySilver.MODID, "silver_nugget_from_blasting_armor"));

        } // end registerFurnaceRecipes()

    } // end subclass OnlySilverDataGenerator$Recipes.
    
} // end-class OnlySilverDataGenerator
