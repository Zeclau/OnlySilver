package mod.zotmc.onlysilver.enchant;

import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class OnlySilverLootModifiers
{

    /**
     * Handles loot modification done by Incantation enchantment.
     */
    public static final class IncantationLootModifier extends LootModifier
    {

        public IncantationLootModifier(ILootCondition[] conditionsIn)
        {
            super(conditionsIn);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
        {
            // TODO Auto-generated method stub
            return null;
        }

        public static class Serializer extends GlobalLootModifierSerializer<IncantationLootModifier>
        {

            public Serializer()
            {
                // TODO Auto-generated constructor stub
            }

            @Override
            public IncantationLootModifier read(ResourceLocation location, JsonObject object,
                    ILootCondition[] ailootcondition)
            {
                return new IncantationLootModifier(ailootcondition);
            }

        } // end-class Serializer
        
    } // end-class IncantationLootModifier

    
} // end-class OnlySilverLootModifiers
