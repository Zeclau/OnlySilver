package mod.zotmc.onlysilver.helpers;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
} // end class
