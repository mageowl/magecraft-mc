package net.mageowlstudios.magecraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.mageowlstudios.magecraft.items.MagecraftItems;
import net.mageowlstudios.magecraft.spells.MagecraftSpells;
import net.mageowlstudios.magecraft.util.Spell;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagecraftMod implements ModInitializer {
//	--CUSTOM REGISTRY
	public static final Registry<Spell> SPELL = createRegistry("spell", Spell.class);

//	--CONSTANTS
	public static final String MOD_ID = "magecraft";

//	--LOGGER
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MagecraftItems.register();
		MagecraftSpells.register();
	}

	@SuppressWarnings("unchecked")
	private static <T> Registry<T> createRegistry(String name, Class<?> clazz) {
		Registry<?> registry = FabricRegistryBuilder.createSimple(clazz, new Identifier(MOD_ID, name)).buildAndRegister();
		return (Registry<T>) registry;
	}

}
