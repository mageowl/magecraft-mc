package com.mageowlstudios.magecraft;

import com.mageowlstudios.magecraft.item.MagecraftItems;
import com.mageowlstudios.magecraft.spell.MagecraftSpells;
import com.mageowlstudios.magecraft.spell.Spell;
import com.mageowlstudios.magecraft.client.MagecraftModelPredicates;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagecraftMod implements ModInitializer {
//    CONSTANTS
    public static final String MOD_ID = "magecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

//    SPELL REGISTER
    public static final Registry<Spell> SPELL_REGISTRY = createRegistry("spell", Spell.class);

    @Override
    public void onInitialize() {
        MagecraftItems.register();
        MagecraftSpells.register();
        MagecraftModelPredicates.register();
    }

    @SuppressWarnings("unchecked")
    private static <T> Registry<T> createRegistry(String name, Class<?> clazz) {
        Registry<?> registry = FabricRegistryBuilder.createSimple(clazz, new Identifier(MOD_ID, name)).buildAndRegister();
        return (Registry<T>) registry;
    }
}
