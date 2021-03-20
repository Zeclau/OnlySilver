package mod.zotmc.onlysilver.client;

import mod.alexndr.simplecorelib.client.ClientUtils;
import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.entity.SilverGolemRenderer;
import mod.zotmc.onlysilver.init.ModBlocks;
import mod.zotmc.onlysilver.init.ModEntities;
import mod.zotmc.onlysilver.init.ModItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@EventBusSubscriber(modid = OnlySilver.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber
{
    /**
     * We need to register our renderers on the client because rendering code does not exist on the server
     * and trying to use it on a dedicated server will crash the game.
     * <p>
     * This method will be called by Forge when it is time for the mod to do its client-side setup
     * This method will always be called after the Registry events.
     * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
     */
    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) 
    {
        // ores with cutouts.
        RenderTypeLookup.setRenderLayer(ModBlocks.silver_ore.get(), (layer) -> layer 
                == RenderType.cutout());
        
        // entity renderer
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.silver_golem.get(), 
                SilverGolemRenderer::new);
        
        // bow models.
        ClientUtils.setupBowModelProperties(ModItems.silver_bow.get());
    } // end onFMLClientSetupEvent

    @SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, i) -> 0xffffff, ModItems.silver_golem_egg.get());
    }
    
} // end class
