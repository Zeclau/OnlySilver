package mod.zotmc.onlysilver.datagen;

import java.util.function.Consumer;

import mod.alexndr.simplecorelib.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.datagen.RecipeSetBuilder;
import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.init.ModBlocks;
import mod.zotmc.onlysilver.init.ModItems;
import mod.zotmc.onlysilver.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

/** 
 * RecipeProvider for OnlySilver.
 * @author Sinhika
 *
 */
public class Recipes extends RecipeProvider implements IConditionBuilder, ISimpleConditionBuilder
{
    private RecipeSetBuilder setbuilder;

    public Recipes(DataGenerator generatorIn)
    {
        super(generatorIn);
        setbuilder = new RecipeSetBuilder(OnlySilver.MODID);
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
        setbuilder.buildSimpleToolSet(consumer, Ingredient.fromTag(ModTags.Items.INGOTS_SILVER),
                "silver", hasItem(ModTags.Items.INGOTS_SILVER), null, false);
        setbuilder.buildModBowRecipe(consumer, ModItems.silver_bow.getId(), Ingredient.fromTag(ModTags.Items.INGOTS_SILVER),
                ModItems.silver_rod.get(), Ingredient.fromTag(Tags.Items.INGOTS_IRON), 
                hasItem(ModTags.Items.INGOTS_SILVER), null);
        
    } // end registerToolRecipes()
    
    protected void registerArmorRecipes(Consumer<IFinishedRecipe> consumer)
    {
        setbuilder.buildSimpleArmorSet(consumer, Ingredient.fromTag(ModTags.Items.INGOTS_SILVER), "silver", 
                hasItem(ModTags.Items.INGOTS_SILVER), null);
    } // end registerArmorRecipes()
    
    protected void registerStorageRecipes(Consumer<IFinishedRecipe> consumer)
    {
        setbuilder.buildSimpleStorageRecipes(consumer, ModItems.silver_ingot.get(), ModBlocks.silver_block.get(), 
                ModItems.silver_nugget.get(), hasItem(ModTags.Items.INGOTS_SILVER));
    } // end registerStorageRecipes()
    
    protected void registerMiscRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(ModItems.silver_wand.get())
            .key('O', ModTags.Items.NUGGETS_SILVER)
            .key('I', ModTags.Items.RODS_SILVER)
            .patternLine("O")
            .patternLine("I")
            .addCriterion("has_item", hasItem(ModTags.Items.RODS_SILVER))
            .build(consumer);
    } // end registerMiscRecipes()

    protected void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer)
    {
        // ore => ingots
        setbuilder.buildOre2IngotRecipes(consumer, Ingredient.fromItems(ModBlocks.silver_ore.get()),
                ModItems.silver_ingot.get(), hasItem(ModBlocks.silver_ore.get()), 0.8F, 200);
        
        // crushed ore => ingots
        setbuilder.buildOre2IngotRecipes(consumer,Ingredient.fromItems(ModItems.crushed_silver_ore.get()), 
                ModItems.silver_ingot.get(), hasItem(ModItems.crushed_silver_ore.get()),  0.8F, 200,
                "_from_chunks");
                
        // dust => ingots
        setbuilder.buildOre2IngotRecipes(consumer, Ingredient.fromItems(ModItems.silver_dust.get()),
                ModItems.silver_ingot.get(),  hasItem(ModItems.silver_dust.get()), 0.8F, 200,
                "_from_dust");
        
        // vanilla recycling, tools/armor => nuggets
        setbuilder.buildVanillaRecyclingRecipes(consumer, 
                Ingredient.fromItems(ModItems.silver_axe.get(), ModItems.silver_bow.get(),
                        ModItems.silver_hoe.get(), ModItems.silver_pickaxe.get(), ModItems.silver_rod.get(),
                        ModItems.silver_shovel.get(), ModItems.silver_sword.get(), ModItems.silver_wand.get(),
                        ModItems.silver_boots.get(), ModItems.silver_chestplate.get(),
                        ModItems.silver_helmet.get(), ModItems.silver_leggings.get()),
                ModItems.silver_nugget.get(), hasItem(ModItems.silver_axe.get()), 0.4F, 200);
        
    } // end registerFurnaceRecipes()

    @Override
    public ICondition flag(String name)
    {
        return impl_flag(OnlySilver.MODID, OnlySilverConfig.INSTANCE, name);
    }

} // end subclass OnlySilverDataGenerator$Recipes.