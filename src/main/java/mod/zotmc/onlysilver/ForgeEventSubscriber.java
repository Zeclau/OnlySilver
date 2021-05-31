package mod.zotmc.onlysilver;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.alexndr.simplecorelib.helpers.LootUtils;
import mod.zotmc.onlysilver.config.OnlySilverConfig;
import mod.zotmc.onlysilver.enchant.IncantationEnchantment;
import mod.zotmc.onlysilver.enchant.SilverAuraEnchantment;
import mod.zotmc.onlysilver.generation.OreGeneration;
import mod.zotmc.onlysilver.helpers.OnlySilverInjectionLookup;
import mod.zotmc.onlysilver.helpers.Utils;
import mod.zotmc.onlysilver.init.ModEnchants;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on both
 * PHYSICAL sides in this class
 *
 */
@EventBusSubscriber(modid = OnlySilver.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber
{
    private static final Logger LOGGER = LogManager.getLogger(OnlySilver.MODID + " Forge Event Subscriber");
    private static final OnlySilverInjectionLookup lootLookupMap = new OnlySilverInjectionLookup();

    /**
     * add mod loot to loot tables. Code heavily based on Botania's LootHandler, which
     * neatly solves the problem when I couldn't figure it out.
     */
    @SubscribeEvent
    public static void LootLoad(final LootTableLoadEvent event)
    {
        if (OnlySilverConfig.addModLootToChests)
        {
            LootUtils.LootLoadHandler(OnlySilver.MODID, event, lootLookupMap);
        } // end-if config allows
    } // end LootLoad()
    
    /* SILVER AURA EVENTS */
    /**
     * The SilverAuraEnchantment extends the lifespan of a dropped item that is
     * enchanted with it--if the SilverAuraEnchantment is enabled. It will only
     * extend lifespan once before item is picked up.
     * 
     * @param event
     */
    @SubscribeEvent
    public static void onItemExpire(final ItemExpireEvent event)
    {
        if (!OnlySilverConfig.enableAuraEnchantment)
        {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent())
        {
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
                    event.setExtraLife(stack.getEntityLifespan(entity.level) * 2);
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
        if (!OnlySilverConfig.enableAuraEnchantment)
        {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent())
        {
            return;
        }
        ItemEntity entity = event.getItem();
        ItemStack stack = entity.getItem();
        if (Utils.hasEnch(stack, ModEnchants.silver_aura.get()) && stack.getItem() != Items.ENCHANTED_BOOK)
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
        if (!OnlySilverConfig.enableAuraEnchantment)
        {
            return;
        }
        // don't try to do anything if the enchantment isn't loaded yet.
        if (!ModEnchants.silver_aura.isPresent())
        {
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
    /* END SILVER_AURA EVENTS */

    /* INCANTATION EVENTS */
    /**
     * The capture list for dropped mob equipment is cleared after passing it to
     * the LivingDropEvent; however, it is valid for this event. We edit valid ItemEntitys in place.
     */
    @SubscribeEvent
    public static void onLivingDropsEvent(final LivingDropsEvent event)
    {
        // skip client-side.
        World ourWorld = event.getEntity().getCommandSenderWorld();
        if (ourWorld.isClientSide)
        {
            return;
        }
        DamageSource damage = event.getSource();
        // did the player kill it?
        if (damage.getEntity() != null && damage.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity killer = (PlayerEntity) damage.getEntity();

            // does player have incantation enchantment on weapon?
            if (Utils.heldItemHasEnch(killer, ModEnchants.incantation.get()))
            {
                // find items dropped...
                Collection<ItemEntity> drops = event.getDrops();
                if (!drops.isEmpty())
                {
                    // drops = new ArrayList<ItemEntity>();
                    for (ItemEntity iEntity : drops)
                    {
                        // double-check that it is an item.
                        // does it qualify for enchantment?
                        ItemStack eStack = iEntity.getItem();
                        if (!eStack.isEmpty() && eStack.isEnchantable())
                        {
                            // try editing it in place?
                            ItemStack incantItem = Utils.getHeldItemWithEnch(killer, ModEnchants.incantation.get());
                            IncantationEnchantment.applyIncantation(killer, incantItem, eStack);
                            LOGGER.debug("Added random enchantment using Incantation!");
                        }
                    } // end-for
                }
            } // end-if weapon has Incantation enchant.
        } // end-if player killed.
    } // end onLivingDropsEvent()
    
    /**
     * Biome loading triggers ore generation.
     */
    @SubscribeEvent(priority=EventPriority.HIGH)
    public static void onBiomeLoading(BiomeLoadingEvent evt)
    {
        if (evt.getCategory() != Biome.Category.THEEND && evt.getCategory() != Biome.Category.NETHER
            && OnlySilverConfig.enableSilverOre)
        {
            OreGeneration.generateOverworldOres(evt);
        }
    } // end onBiomeLoading()

} // end-class
