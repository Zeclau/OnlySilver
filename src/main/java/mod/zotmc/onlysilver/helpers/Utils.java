package mod.zotmc.onlysilver.helpers;

import java.util.Map;

import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class Utils
{

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
    
    public static boolean heldItemHasEnch(PlayerEntity player, Enchantment ench) 
    {
        if (Utils.hasEnch(player.getHeldItemMainhand(), ench))
        {
            return true;
        }
        else if (Utils.hasEnch(player.getHeldItemOffhand(), ench))
        {
            return true;
        }
        return false;
    } // end heldItemHasEnch()
    
    public static ItemStack getHeldItemWithEnch(PlayerEntity player, Enchantment ench)
    {
        ItemStack heldItem = ItemStack.EMPTY;
        if (Utils.hasEnch(player.getHeldItemMainhand(), ModEnchants.incantation.get()))
        {
            heldItem = player.getHeldItemMainhand();
        }
        else if (Utils.hasEnch(player.getHeldItemOffhand(), ModEnchants.incantation.get()))
        {
            heldItem = player.getHeldItemOffhand();
        }
        return heldItem;
    } // end getHeldItemWithEnch()
    
} // end class
