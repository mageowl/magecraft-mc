package net.mageowlstudios.magecraft.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class SpellBookItem extends Item {

    public static final String SPELL_ID_NBT = "SpellId";

    public SpellBookItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public static String getSpellID(ItemStack stack) {
        return stack.getOrCreateNbt().getString(SPELL_ID_NBT);
    }

    public static void setSpellID(ItemStack stack, String spellID) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString(SPELL_ID_NBT, spellID);
    }
}
