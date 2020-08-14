package mod.zotmc.onlysilver.enchant;

import mod.zotmc.onlysilver.api.OnlySilverRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

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
        return (OnlySilverRegistry.isSilverEquip(stack) && super.canApplyAtEnchantingTable(stack));
    }

    @Override
    public int calcModifierDamage(int level, DamageSource source)
    {
        // TODO Auto-generated method stub
        return super.calcModifierDamage(level, source);
    }

    @Override
    public float calcDamageByCreature(int level, CreatureAttribute creatureType)
    {
        // TODO Auto-generated method stub
        return super.calcDamageByCreature(level, creatureType);
    }

    @Override
    public boolean isAllowedOnBooks()
    {
        return false;
    }

} // end class
