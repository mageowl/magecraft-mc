package com.mageowlstudios.magecraft.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SpellDustItem extends Item {
    public static final String SPELL_ID_NBT = "Spell";

    public SpellDustItem(Settings settings) {
        super(settings);
    }

    @Nullable
    public static Identifier getSpellID(ItemStack stack) {
        String string = stack.getOrCreateNbt().getString(SPELL_ID_NBT);
        if (string.isBlank()) return null;
        return new Identifier(string);
    }

    public static void setSpellID(ItemStack stack, String spellID) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString(SPELL_ID_NBT, spellID);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Identifier id = getSpellID(stack);
        if (id == null) return;
        tooltip.add(Text.translatable("spell." + id.getNamespace() + "." + id.getPath()).formatted(Formatting.GRAY));
    }
}
