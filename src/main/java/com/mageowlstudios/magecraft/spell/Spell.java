package com.mageowlstudios.magecraft.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Spell {
    public final int manaCost;
    public final float castTime;
    public final int color;
    public SpellHandler handler = (World _w, LivingEntity _u, int _c) -> true;

    public Spell(SpellSettings settings) {
        manaCost = settings.manaCost;
        castTime = settings.castTime;
        color = settings.color;
    }

    public Spell(SpellSettings settings, SpellHandler callback) {
        manaCost = settings.manaCost;
        castTime = settings.castTime;
        color = settings.color;
        handler = callback;
    }

    public static class SpellSettings {
        public int manaCost;
        public float castTime;
        public int color;

        public SpellSettings manaCost(int xp) {
            manaCost = xp;
            return this;
        }

        public SpellSettings castTime(float sec) {
            castTime = sec;
            return this;
        }

        public SpellSettings color(int dec) {
            color = dec;
            return this;
        }
    }

    public interface SpellHandler {
        boolean cast(World world, LivingEntity player, int charge);
    }
}
