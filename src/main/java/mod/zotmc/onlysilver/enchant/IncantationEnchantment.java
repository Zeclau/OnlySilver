package mod.zotmc.onlysilver.enchant;

import mod.zotmc.onlysilver.api.OnlySilverRegistry;
import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class IncantationEnchantment extends Enchantment
{

    public IncantationEnchantment()
    {
        super(Rarity.RARE, EnchantmentType.BREAKABLE, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }

    @Override
    public int getMinCost(int enchantmentLevel)
    {
        return 15 + 9 * (enchantmentLevel - 1);
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
    public boolean isAllowedOnBooks()
    {
        return false;
    }
    
    /**
     * utility function to actually do the enchantment and damage the incanting weapon.
     * @param player
     * @param heldItem
     * @param target
     * @return
     */
    public static ItemStack applyIncantation(PlayerEntity player, ItemStack heldItem, ItemStack target)
    {
        int ilvl = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.incantation.get(), heldItem);
        int strength = ilvl * 10 - 5;
        EnchantmentHelper.enchantItem(player.level.getRandom(), target, strength, true);
        // damage the weapon applying the incantation. This could break it.
        heldItem.hurtAndBreak(strength*2, player, (foo) -> {foo.broadcastBreakEvent(EquipmentSlotType.MAINHAND);});
        return target;
    } // end applyIncantation()
} // end class
