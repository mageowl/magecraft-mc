package com.mageowlstudios.magecraft.item.custom;

import com.mageowlstudios.magecraft.MagecraftMod;
import com.mageowlstudios.magecraft.spell.Spell;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class StaffItem extends Item {
    public StaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean hasDust = !getSelectedDust(user, hand).isEmpty();
        if (hasDust) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public static ItemStack getSelectedDust(LivingEntity player, Hand staffHand) {
        ItemStack stack = player.getStackInHand(staffHand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);
        if (stack.getRegistryEntry().matchesId(Identifier.tryParse("magecraft:spell_dust"))) {
            return stack;
        } else return ItemStack.EMPTY;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient) return;
        ItemStack dust = getSelectedDust(user, user.getActiveHand());
        Identifier id = SpellDustItem.getSpellID(dust);
        Spell spell = MagecraftMod.SPELL_REGISTRY.get(id);

        int charge = getMaxUseTime(stack) - remainingUseTicks;
        MagecraftMod.LOGGER.info("Charged to: " + String.valueOf(charge));

        if (id == null || spell == null) return;
        if (charge < spell.castTime) return;

        boolean consume = spell.handler.cast(world, user, remainingUseTicks);
        boolean creativeMode = user.isPlayer() && ((PlayerEntity) user).isCreative();
        if (consume && !creativeMode) {
            dust.decrement(1);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) return stack;

        if (!(user instanceof PlayerEntity)) {
            return stack;
        }
        PlayerEntity player = (PlayerEntity) user;

        player.getItemCooldownManager().set(this, 10);
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 1500;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }
}
