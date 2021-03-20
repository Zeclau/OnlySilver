package mod.zotmc.onlysilver.enchant;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.api.OnlySilverRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import net.minecraft.enchantment.Enchantment.Rarity;

public class SilverAuraEnchantment extends Enchantment
{
    public static final String extendedLifeTag = OnlySilver.MODID + "-lifeExtended";
    
    public SilverAuraEnchantment()
    {
        super(Rarity.UNCOMMON, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
    }

    @Override
    public int getMinCost(int enchantmentLevel)
    {
        return 5 + 20 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxCost(int enchantmentLevel)
    {
        return super.getMaxCost(enchantmentLevel) + 50;
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
    public int getDamageProtection(int level, DamageSource source)
    {
        // TODO Auto-generated method stub
        return super.getDamageProtection(level, source);
    }

    @Override
    public float getDamageBonus(int level, CreatureAttribute creatureType)
    {
        // TODO Auto-generated method stub
        return super.getDamageBonus(level, creatureType);
    }

    @Override
    public boolean isAllowedOnBooks()
    {
        return true;
    }

} // end class
