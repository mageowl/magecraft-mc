package com.mageowlstudios.magecraft.client;

import com.mageowlstudios.magecraft.MagecraftMod;
import com.mageowlstudios.magecraft.item.MagecraftItems;
import com.mageowlstudios.magecraft.item.custom.SpellDustItem;
import com.mageowlstudios.magecraft.item.custom.StaffItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class MagecraftModelPredicates {

    public static void register() {
        ModelPredicateProviderRegistry.register(MagecraftItems.AMETHYST_STAFF, new Identifier("magecraft:charge"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                Identifier spellId = SpellDustItem.getSpellID(StaffItem.getSelectedDust(entity, entity.getActiveHand()));
                return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft())
                        / (spellId == null ? 20.0F : MagecraftMod.SPELL_REGISTRY.get(spellId).castTime);
            }
        });
        ModelPredicateProviderRegistry.register(MagecraftItems.AMETHYST_STAFF, new Identifier("magecraft:charging"), (stack, world, entity, seed) ->
            entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
