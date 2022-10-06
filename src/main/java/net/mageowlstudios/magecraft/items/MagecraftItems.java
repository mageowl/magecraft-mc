package net.mageowlstudios.magecraft.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.mageowlstudios.magecraft.MagecraftMod;
import net.mageowlstudios.magecraft.items.custom.StaffItem;
import net.mageowlstudios.magecraft.items.custom.SpellBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MagecraftItems {

    public static final Item AMETHYST_STAFF = registerItem("staff",
            new StaffItem(new FabricItemSettings().group(ItemGroup.TOOLS).maxDamage(1000)));
    public static final Item SPELL_BOOK = registerItem("spell_book", new SpellBookItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(ItemGroup.TOOLS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(MagecraftMod.MOD_ID, name), item);
    }

    public static void register() {
        MagecraftMod.LOGGER.debug("Registering items for " + MagecraftMod.MOD_ID);
    }
}
