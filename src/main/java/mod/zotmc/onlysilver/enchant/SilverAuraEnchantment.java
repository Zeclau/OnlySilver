package mod.zotmc.onlysilver.enchant;

import mod.zotmc.onlysilver.api.OnlySilverRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class SilverAuraEnchantment extends Enchantment
{

    public SilverAuraEnchantment()
    {
        super(Rarity.UNCOMMON, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5 + 20 * (enchantmentLevel - 1);
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
        // TODO Auto-generated method stub
        return (OnlySilverRegistry.isSilverEquip(stack) && super.canApplyAtEnchantingTable(stack));
    }

} // end class
