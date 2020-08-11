package mod.zotmc.onlysilver.init;

import mod.zotmc.onlysilver.OnlySilver;
import mod.zotmc.onlysilver.enchant.IncantationEnchantment;
import mod.zotmc.onlysilver.enchant.SilverAuraEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModEnchants
{
    public static final DeferredRegister<Enchantment> ENCHANTS = 
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, OnlySilver.MODID);
    
    public static final RegistryObject<SilverAuraEnchantment> silver_aura = 
            ENCHANTS.register("silver_aura", () -> new SilverAuraEnchantment());
    
    public static final RegistryObject<IncantationEnchantment> incantation = 
            ENCHANTS.register("incantation", () -> new IncantationEnchantment());
    
} // end class
