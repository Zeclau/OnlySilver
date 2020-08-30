package mod.zotmc.onlysilver.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SilverBowItem extends BowItem
{

    public SilverBowItem(Properties builder)
    {
        super(builder);
    }

    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
    {
        // add the default enchantments for Mythril bow.
        Map<Enchantment,Integer> oldEnchants = EnchantmentHelper.getEnchantments(stack);
        stack = this.addSilverEnchantments(oldEnchants, stack);

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);

        // remove temporary intrinsic enchantments.
        EnchantmentHelper.setEnchantments(oldEnchants, stack);
    }

    /**
     * Add default enchantments intrinsic to silver bow.
     * @param oldEnch
     * @param stack
     * @return
     */
    private ItemStack addSilverEnchantments(Map<Enchantment,Integer> oldEnch, ItemStack stack)
    {
        if (stack.isEmpty()) return stack;
        Map<Enchantment,Integer> enchMap = new HashMap<>(oldEnch);

        // increment PUNCH strength by 2.
        if (!enchMap.containsKey(Enchantments.PUNCH))
        {
            enchMap.put(Enchantments.PUNCH, 2);
        }
        else // contains PUNCH enchantment; make it stronger. 
        {
            int k = enchMap.get(Enchantments.PUNCH);
            k += 2;
            enchMap.replace(Enchantments.PUNCH, k);
        }
        
        // add intrinsic enchantments, if any.
        if (enchMap.size() > 0) {
            EnchantmentHelper.setEnchantments(enchMap, stack);
        }
        return stack;
    } // end addSilverEnchantments()
    
    
    @Override
    public int getItemEnchantability()
    {
        return OnlySilverItemTier.SILVER.getEnchantability() / 3;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("onlysilver.tooltip.knockback")));
    }
    
} // end-class
