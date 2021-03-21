package mod.zotmc.onlysilver.helpers;

import java.util.Map;

import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class Utils
{
    /**
     * Check if item has a given enchantment on it.
     * @param stack the ItemStack to check
     * @param ench the Enchantment to check for
     * @return true if stack has the enchantment, false if it does not.
     */
    public static boolean hasEnch(ItemStack stack, Enchantment ench)
    {
        if (! stack.isEmpty())
        {
            Map<Enchantment, Integer> ench_map = EnchantmentHelper.getEnchantments(stack);
            if (ench_map.containsKey(ench)) 
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check to see if the player is holding an item with a given enchantment in either hand.
     * @param player PlayerEntity to check for the enchantment
     * @param ench Enchantment to check for.
     * @return true if held item in either hand has the enchantment, false if neither does.
     */
    public static boolean heldItemHasEnch(PlayerEntity player, Enchantment ench) 
    {
        if (Utils.hasEnch(player.getMainHandItem(), ench))
        {
            return true;
        }
        else if (Utils.hasEnch(player.getOffhandItem(), ench))
        {
            return true;
        }
        return false;
    } // end heldItemHasEnch()
    
    /**
     * Get the item held by player that has the given enchantment.
     * @param player PlayerEntity to get enchanted held item from.
     * @param ench Enchantment to find in held item
     * @return ItemStack of held item that has enchantment, or ItemStack.EMPTY if 
     *  an item with the enchantment is not held at all.
     */
    public static ItemStack getHeldItemWithEnch(PlayerEntity player, Enchantment ench)
    {
        ItemStack heldItem = ItemStack.EMPTY;
        if (Utils.hasEnch(player.getMainHandItem(), ModEnchants.incantation.get()))
        {
            heldItem = player.getMainHandItem();
        }
        else if (Utils.hasEnch(player.getOffhandItem(), ModEnchants.incantation.get()))
        {
            heldItem = player.getOffhandItem();
        }
        return heldItem;
    } // end getHeldItemWithEnch()
    
} // end class
