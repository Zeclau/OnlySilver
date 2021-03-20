package mod.zotmc.onlysilver.entity;

import mod.zotmc.onlysilver.init.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SilverGolemEntity extends IronGolemEntity
{
    public SilverGolemEntity(EntityType<? extends IronGolemEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute prepareAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.silvergolem_hit.get();
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.silvergolem_death.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        super.playSound(SoundEvents.IRON_GOLEM_STEP, 1.3322327F, 1);
    }

    @Override
    public boolean canBeAffected(EffectInstance potioneffectIn)
    {
        Effect effect = potioneffectIn.getEffect();
        if (effect == Effects.POISON) {
            return false;
        }
        return super.canBeAffected(potioneffectIn);
    }

    @Override public void playSound(SoundEvent sound, float volume, float pitch) 
    {
        if (sound != SoundEvents.IRON_GOLEM_ATTACK) {
            volume *= 1.6271853F;
        }
        super.playSound(sound, volume, pitch);
    }

} // end class SilverGolemEntity
