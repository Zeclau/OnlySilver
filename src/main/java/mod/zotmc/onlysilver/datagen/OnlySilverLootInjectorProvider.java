package mod.zotmc.onlysilver.datagen;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import mod.alexndr.simplecorelib.datagen.LootTableInjectorProvider;
import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

public class OnlySilverLootInjectorProvider extends LootTableInjectorProvider
{

    public OnlySilverLootInjectorProvider(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables()
    {
        tables.clear();
        
        // abandoned mineshaft
        LootPool.Builder foo = createChestPool(1, 1, 0.75F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_ingot.get()).setWeight(1)
                        .apply(SetCount.setCount(RandomValueRange.between(1, 2))));
        addInjectionTable(OnlySilver.MODID, "abandoned_mineshaft", foo);                

        // desert_pyramid
        foo = createChestPool(1, 1, 0.25F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_ingot.get()).setWeight(5)
                        .apply(SetCount.setCount(RandomValueRange.between(2, 5))))
                .add(ItemLootEntry.lootTableItem(ModItems.silver_helmet.get()).setWeight(1))
                .add(ItemLootEntry.lootTableItem(ModItems.silver_bow.get()).setWeight(1));
        addInjectionTable(OnlySilver.MODID, "desert_pyramid", foo);                

        // simple_dungeon
        foo = createChestPool(1, 1, 0.50F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_ingot.get()).setWeight(1)
                        .apply(SetCount.setCount(RandomValueRange.between(2, 3))));
        addInjectionTable(OnlySilver.MODID, "simple_dungeon", foo);                

        // buried_treasure
        foo = createChestPool(1, 1, 0.50F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_ingot.get()).setWeight(5)
                        .apply(SetCount.setCount(RandomValueRange.between(1, 4))))
                .add(ItemLootEntry.lootTableItem(ModItems.silver_sword.get()).setWeight(1));
        addInjectionTable(OnlySilver.MODID, "buried_treasure", foo);                
                
        // village_fletcher
        foo = createChestPool(1, 1, 0.25F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_rod.get()).setWeight(1));
        addInjectionTable(OnlySilver.MODID, "village_fletcher", foo);                
       
        // village_temple
        foo = createChestPool(1, 1, 0.25F)
                .add(ItemLootEntry.lootTableItem(ModItems.silver_nugget.get()).setWeight(3)
                        .apply(SetCount.setCount(RandomValueRange.between(1, 2))))
                .add(ItemLootEntry.lootTableItem(ModItems.silver_wand.get()).setWeight(1));
        addInjectionTable(OnlySilver.MODID, "village_temple", foo);                
        
        return tables;
    } // end getTables()

} // end class
