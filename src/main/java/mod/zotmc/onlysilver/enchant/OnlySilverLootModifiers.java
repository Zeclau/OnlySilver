package mod.zotmc.onlysilver.enchant;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.helpers.Utils;
import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class OnlySilverLootModifiers
{
    private static final Logger LOGGER = LogManager.getLogger(OnlySilver.MODID + " OnlySilverLootModifiers");

    /**
     * Handles loot modification done by Incantation enchantment.
     */
    public static final class IncantationLootModifier extends LootModifier
    {

        public IncantationLootModifier(ILootCondition[] conditionsIn)
        {
            super(conditionsIn);
        }

        @Override
        protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
        {
            // LOGGER.debug("In IncantationLootModifier.doApply");
            // don't do anything if Incantation not enabled.
            if (!OnlySilverConfig.enableIncantationEnchantment)
            {
                // LOGGER.debug("IncantationEnchantment disabled");
                return generatedLoot;
            }
            // loot dropper must be a mob, killed by a player, otherwise no action.
            if (!(context.has(LootParameters.LAST_DAMAGE_PLAYER) && context.has(LootParameters.THIS_ENTITY)))
            {
                LOGGER.debug("not dropped by a player-killed mob");
                return generatedLoot;
            }
            else
            {
                // must be a mob.
                Entity loot_dropper = context.get(LootParameters.THIS_ENTITY);
                if (loot_dropper instanceof LivingEntity)
                {
                    PlayerEntity player = context.get(LootParameters.LAST_DAMAGE_PLAYER);

                    ItemStack weapon = Utils.getHeldItemWithEnch(player, ModEnchants.incantation.get());
                    if (weapon.isEmpty()) 
                    {
                        // LOGGER.debug("player weapon does not have Incantation enchantment");
                    }
                    // make sure killing player has the Incantation enchant, and find the weapon it
                    // is on.
                    else 
                    {
                        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
                        
                        for (ItemStack drop : generatedLoot)
                        {
                            // is the drop unenchanted and enchantable, and is the weapon unbroken?
                            if (weapon.isEmpty()) {
                                LOGGER.debug("Incantation weapon is broken");
                                ret.add(drop);
                            }
                            else if (drop.isEmpty()) {
                                if (drop != ItemStack.EMPTY) {
                                    LOGGER.debug("Broken/Missing item is " + drop.getDisplayName().getString());
                                }
                                ret.add(drop);
                            }
                            else if (!drop.isEnchantable()) {
                                // LOGGER.debug("Drop (" + drop.getDisplayName().getString() + ") is not enchantable");
                                ret.add(drop);
                            }
                            else  // valid for random enchant.
                            {
                                // LOGGER.debug("Apply random enchantment to " +  drop.getDisplayName().getString() + "!");
                                ItemStack newdrop = drop.copy();
                                IncantationEnchantment.applyIncantation(player, weapon, newdrop);
                                LOGGER.debug("Random enchantment applied to " +  newdrop.getDisplayName().getString() + "!");
                                ret.add(newdrop);
                            } // end-else
                        } // end-for
                        return ret;
                    } // end-if incantation weapon exists
                } // end-if loot_dropper
            } //end-else
            // general failure case.
            return generatedLoot;
        } // end doApply()

        public static class Serializer extends GlobalLootModifierSerializer<IncantationLootModifier>
        {
            @Override
            public IncantationLootModifier read(ResourceLocation location, JsonObject object,
                    ILootCondition[] ailootcondition)
            {
                return new IncantationLootModifier(ailootcondition);
            }

        } // end-class Serializer
    } // end-class IncantationLootModifier

} // end-class OnlySilverLootModifiers
