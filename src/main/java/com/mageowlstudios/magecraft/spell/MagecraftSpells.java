package com.mageowlstudios.magecraft.spell;

import com.mageowlstudios.magecraft.MagecraftMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MagecraftSpells {
    public static final Spell MAGIC_MISSILE = registerSpell("magic_missile", new Spell(new Spell.SpellSettings().castTime(10).color(0xeb34d5).manaCost(4)));
    public static final Spell FIREBALL = registerSpell("fireball", new Spell(new Spell.SpellSettings().castTime(30).color(0xff0000).manaCost(4), (World world, LivingEntity user, int charge) -> {
        int power = charge / 30;
        Vec3d rot = user.getRotationVector();

        FireballEntity fireball = new FireballEntity(world, user, rot.x * power, rot.y * power, rot.z * power, 1);
        fireball.setPosition(user.getEyePos());
        world.spawnEntity(fireball);

        return true;
    }));

    private static Spell registerSpell(String name, Spell spell) {
        return Registry.register(MagecraftMod.SPELL_REGISTRY, new Identifier(MagecraftMod.MOD_ID, name), spell);
    }

    public static void register() {
        MagecraftMod.LOGGER.debug("Registering spells for " + MagecraftMod.MOD_ID);
    }
}
