package net.mageowlstudios.magecraft.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Spell {
    public final int manaCost;
    public final int castTime;
    public final SpellHandler handler;

    public Spell(Settings settings, SpellHandler handler) {
        this.manaCost = settings._manaCost;
        this.castTime = settings._castTime;
        this.handler = handler;
    }

    public boolean handleCast(World world, PlayerEntity player, int charge) {
        return handler.cast(world, player, charge);
    }

    public static class Settings {
        public int _manaCost = 4;
        public int _castTime = 5;

        public Settings manaCost(int xp) {
            _manaCost = xp;
            return this;
        }

        public Settings castTime(int sec) {
            _castTime = sec;
            return this;
        }
    }

    public interface SpellHandler {
        boolean cast(World world, PlayerEntity player, int charge);
    }
}
