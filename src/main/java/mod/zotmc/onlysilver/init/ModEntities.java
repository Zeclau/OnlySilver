package mod.zotmc.onlysilver.init;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.entity.SilverGolemEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, OnlySilver.MODID);
    
    public static final RegistryObject<EntityType<SilverGolemEntity>> silver_golem = 
            ENTITIES.register("silver_golem", 
                    () -> EntityType.Builder.create(SilverGolemEntity::new, EntityClassification.MISC)
                    .size(0.8F, 1.9F)
                    .build("silver_golem"));
    
} // end class
