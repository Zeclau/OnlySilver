package mod.zotmc.onlysilver.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import mod.zotmc.onlysilver.OnlySilver;

import java.util.function.Supplier;

public final class ModTabGroups
{

    public static final ItemGroup MOD_ITEM_GROUP = 
            new ModTabGroup(OnlySilver.MODID, () -> new ItemStack(ModItems.silver_ingot.get()));

    public static final class ModTabGroup extends ItemGroup
    {

        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        /**
         * @param name  String: mod id
         * @param iconSupplier
         */
        public ModTabGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack createIcon() {
            return iconSupplier.get();
        }

    } // end ModTabGroup()

}  // end class ModTabGroup
