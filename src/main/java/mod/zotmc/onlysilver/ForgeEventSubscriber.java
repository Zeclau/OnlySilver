package mod.zotmc.onlysilver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.enchant.SilverAuraEnchantment;
import mod.zotmc.onlysilver.helpers.Utils;
import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on both PHYSICAL sides in this class
 *
 */
@EventBusSubscriber(modid = OnlySilver.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber
{
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogManager.getLogger(OnlySilver.MODID + " Forge Event Subscriber");

    /* SILVER AURA EVENTS */
    /**
     * The SilverAuraEnchantment extends the lifespan of a dropped item that is enchanted with 
     * it--if the SilverAuraEnchantment is enabled. It will only extend lifespan once before item
     * is picked up.
     * 
     * @param event
     */
    @SubscribeEvent
    public static void onItemExpire(final ItemExpireEvent event)
    {
        if (!OnlySilverConfig.enableAuraEnchantment) {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent()) {
            return;
        }
        ItemEntity entity = event.getEntityItem();
        ItemStack stack = entity.getItem();
        if (Utils.hasEnch(stack, ModEnchants.silver_aura.get())) 
        {
            if (stack.getItem() == Items.ENCHANTED_BOOK) 
            {
                event.setExtraLife(96000);
                event.setCanceled(true);                
            }
            else 
            {
                CompoundNBT tag = stack.getOrCreateTag();
                if (!tag.getBoolean(SilverAuraEnchantment.extendedLifeTag)) 
                {
                    event.setExtraLife(stack.getEntityLifespan(entity.world)*2);
                    event.setCanceled(true);
                    tag.putBoolean(SilverAuraEnchantment.extendedLifeTag, true);
                }
            } // end-else
        } // end-if has silver_aura enchant
    } // end onItemExpire()

    /**
     * if a ItemEntity with a SilverAuraEnchantment is picked up, it resets the 
     * extendedLifeTag property to false.
     */
    @SubscribeEvent
    public static void onItemPickup(final EntityItemPickupEvent event)
    {
        if (!OnlySilverConfig.enableAuraEnchantment) {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent()) {
            return;
        }
        ItemEntity entity = event.getItem();
        ItemStack stack = entity.getItem();
        if (Utils.hasEnch(stack, ModEnchants.silver_aura.get()) 
                && stack.getItem() != Items.ENCHANTED_BOOK) 
        {
            CompoundNBT tag = stack.getOrCreateTag();
            if (tag.getBoolean(SilverAuraEnchantment.extendedLifeTag)) 
            {
                tag.putBoolean(SilverAuraEnchantment.extendedLifeTag, false);
            }
        }
    } // end onItemPickup()
    
    /** 
     * items enchanted with SilverAura do not burn up in lava or fire. It will still 
     * eventually despawn.
     */
    @SubscribeEvent
    public static void onEntityJoinWorld(final EntityJoinWorldEvent event)
    {
        if (!OnlySilverConfig.enableAuraEnchantment ) 
        {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent()) {
            return;
        }
        // is it even an item?
        if (event.getEntity() instanceof ItemEntity)
        {
            ItemEntity entity = (ItemEntity) event.getEntity();
            ItemStack stack = entity.getItem();
            if (Utils.hasEnch(stack, ModEnchants.silver_aura.get()))
            {
                entity.setInvulnerable(true);
            } // end-if has silver_aura
        } // end-if ItemEntity
    } // end onEntityJoinWorld()
     
} // end-class
