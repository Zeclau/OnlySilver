package mod.zotmc.onlysilver.enchant;

import mod.zotmc.onlysilver.api.OnlySilverRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class IncantationEnchantment extends Enchantment
{

    public IncantationEnchantment()
    {
        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 15 + 9 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMaxEnchantability(enchantmentLevel) + 50;
    }

    /**
     * only allow this enchantment on silver items.
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return (OnlySilverRegistry.isSilverEquip(stack) && super.canApplyAtEnchantingTable(stack));
    }

    @Override
    public boolean isAllowedOnBooks()
    {
        return false;
    }
    
} // end class
