package com.mageowlstudios.magecraft.item;

import com.mageowlstudios.magecraft.MagecraftMod;
import com.mageowlstudios.magecraft.item.custom.SpellDustItem;
import com.mageowlstudios.magecraft.item.custom.StaffItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MagecraftItems {
    public static final Item AMETHYST_STAFF = registerItem("amethyst_staff",
            new StaffItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(1000)));
    public static final Item SPELL_DUST = registerItem("spell_dust", new SpellDustItem(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_DUST = registerItem("amethyst_dust", new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MagecraftMod.MOD_ID, name), item);
    }

    public static void register() {
        MagecraftMod.LOGGER.debug("Registering items for " + MagecraftMod.MOD_ID);

        ColorProviderRegistry.ITEM.register(
                ((stack, tintIndex) -> SpellDustItem.getSpellID(stack) != null &&
                        MagecraftMod.SPELL_REGISTRY.get(SpellDustItem.getSpellID(stack)) != null ?
                        MagecraftMod.SPELL_REGISTRY.get(SpellDustItem.getSpellID(stack)).color : 0xFFFFFF
                ), SPELL_DUST);
    }
}
