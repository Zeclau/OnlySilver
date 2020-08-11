package mod.zotmc.onlysilver.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class IncantationEnchantment extends Enchantment
{

    public IncantationEnchantment()
    {
        super(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

} // end class
