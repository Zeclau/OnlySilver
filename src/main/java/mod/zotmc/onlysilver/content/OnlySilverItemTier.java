package mod.zotmc.onlysilver.content;

import java.util.function.Supplier;

import mod.zotmc.onlysilver.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum OnlySilverItemTier implements IItemTier
{
   SILVER(2, 226, 8.0F, 2.0F, 30, ()->{ return Ingredient.of( ModItems.silver_ingot.get()); });

   private final int harvestLevel;
   private final int maxUses;
   private final float efficiency;
   private final float attackDamage;
   private final int enchantability;
   private final LazyValue<Ingredient> repairMaterial;

   private OnlySilverItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn,
                    Supplier<Ingredient> repairMaterialIn)
   {
      this.harvestLevel = harvestLevelIn;
      this.maxUses = maxUsesIn;
      this.efficiency = efficiencyIn;
      this.attackDamage = attackDamageIn;
      this.enchantability = enchantabilityIn;
      this.repairMaterial = new LazyValue<>(repairMaterialIn);
   }

   @Override
   public int getUses() {
      return this.maxUses;
   }

   @Override
   public float getSpeed() {
      return this.efficiency;
   }

   @Override
   public float getAttackDamageBonus() {
      return this.attackDamage;
   }

   @Override
   public int getLevel() {
      return this.harvestLevel;
   }

   @Override
   public int getEnchantmentValue() {
      return this.enchantability;
   }

   @Override
   public Ingredient getRepairIngredient() {
      return this.repairMaterial.get();
   }
}  // end class SimpleOresItemTier
