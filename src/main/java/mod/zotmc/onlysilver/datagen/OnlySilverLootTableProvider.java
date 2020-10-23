package mod.zotmc.onlysilver.datagen;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import mod.zotmc.onlysilver.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootTable.Builder;

public class OnlySilverLootTableProvider extends AbstractLootTableProvider
{

    public OnlySilverLootTableProvider(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables()
    {
        tables.clear();
        standardDropTable(ModBlocks.silver_ore.get());
        standardDropTable(ModBlocks.silver_block.get());
        return tables;
    }

} // end class
