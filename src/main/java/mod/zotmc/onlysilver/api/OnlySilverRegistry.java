package mod.zotmc.onlysilver.api;

import java.util.Queue;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import com.google.common.collect.Queues;
import net.minecraft.item.ItemStack;

public class OnlySilverRegistry
{
    static final Queue<Predicate<? super ItemStack>> silverPredicates = Queues.newArrayDeque();

    /**
     * Register your own {@linkplain Predicate predicate} to help OnlySilver determining whether or not an item is made from silver.
     * The current purpose for this method is to determine whether an instrument should have silver enchantments.
     *
     * <p>At runtime use of {@link OnlySilverUtils#isSilverEquip}, registered {@linkplain Predicate predicates}
     * are applied one by one until they have been all applied or once {@code true} is returned. (Short circuit evaluation)
     *
     * <p>{@linkplain Predicate Predicates} are guaranteed to have non-{@code null} input.
     *
     * @see OnlySilverUtils#isSilverEquip
     */
    public static void registerSilverPredicate(Predicate<? super ItemStack> silverPredicate) 
    {
      silverPredicates.add(silverPredicate);
    }
    
    /**
     * Check whether or not an equipment (tool or armor) is made from silver like substances.
     * The current purpose for this method is to determine whether an instrument should have silver enchantments.
     *
     * @see OnlySilverRegistry#registerSilverPredicate
     */
    public static boolean isSilverEquip(@Nullable ItemStack item) 
    {
      if (item != null) 
      {
        for (Predicate<? super ItemStack> p : silverPredicates)
          if (p.test(item))
            return true;
      }
      return false;
    } // end isSilverEquip()

} // end class OnlySilverRegistry
