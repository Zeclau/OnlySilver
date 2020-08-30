package mod.zotmc.onlysilver.init;

import mod.zotmc.onlysilver.OnlySilver;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * where we register custom sound effects.
 *
 */
public final class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = 
            new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, OnlySilver.MODID);
    
    public static final RegistryObject<SoundEvent> silvergolem_hit = SOUNDS.register("silvergolem_hit",
            () -> new SoundEvent(new ResourceLocation(OnlySilver.MODID, "silvergolem.hit")));
    
    public static final RegistryObject<SoundEvent> silvergolem_death = SOUNDS.register("silvergolem_death",
            () -> new SoundEvent(new ResourceLocation(OnlySilver.MODID, "silvergolem.death")));
   
} // end class
