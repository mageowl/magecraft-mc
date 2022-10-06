package net.mageowlstudios.magecraft.spells;

import net.mageowlstudios.magecraft.MagecraftMod;
import net.mageowlstudios.magecraft.util.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MagecraftSpells {
    public final Spell MAGIC_MISSILE = registerSpell("magic_missile", new Spell(new Spell.Settings().castTime(4).manaCost(3), (World world, PlayerEntity player, int charge) -> {
        return true;
    }));

    private static Spell registerSpell(String name, Spell spell) {
        return Registry.register(MagecraftMod.SPELL, new Identifier(MagecraftMod.MOD_ID, name), spell);
    }

    public static void register() {
        MagecraftMod.LOGGER.debug("Registering spells for " + MagecraftMod.MOD_ID);
    }
}
