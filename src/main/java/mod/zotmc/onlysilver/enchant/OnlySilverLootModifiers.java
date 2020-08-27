package mod.zotmc.onlysilver.enchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.helpers.Utils;
import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
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
            LOGGER.debug("In IncantationLootModifier.doApply");
            // don't do anything if Incantation not enabled.
            if (!OnlySilverConfig.enableIncantationEnchantment)
            {
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
                    ItemStack weapon = ItemStack.EMPTY;
                    if (Utils.hasEnch(player.getHeldItemMainhand(), ModEnchants.incantation.get()))
                    {
                        weapon = player.getHeldItemMainhand();
                    }
                    else if (Utils.hasEnch(player.getHeldItemOffhand(), ModEnchants.incantation.get()))
                    {
                        weapon = player.getHeldItemOffhand();
                    }
                    // make sure killing player has the Incantation enchant, and find the weapon it
                    // is on.
                    if (!weapon.isEmpty())
                    {
                        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
                        int ilvl = EnchantmentHelper.getEnchantmentLevel(ModEnchants.incantation.get(), weapon);
                        int strength = ilvl * 10 - 5;
                        
                        for (ItemStack drop : generatedLoot)
                        {
                            // is the drop unenchanted and enchantable, and is the weapon unbroken?
                            if (!weapon.isEmpty() && !drop.isEmpty() && !drop.isEnchanted() && drop.isEnchantable()) 
                            {
                                Random rand = context.getWorld().getRandom();
                                EnchantmentHelper.addRandomEnchantment(rand, drop, strength, false);
                                LOGGER.debug("Random enchantment applied!");
                                
                                // damage the weapon applying the incantation. This could break it.
                                weapon.damageItem(strength*2, player, (foo) -> {foo.sendBreakAnimation(EquipmentSlotType.MAINHAND);});
                            }
                            ret.add(drop);
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
