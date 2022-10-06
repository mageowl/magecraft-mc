package net.mageowlstudios.magecraft.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StaffItem extends Item {
    public StaffItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        boolean hasBook = !getSelectedBook(user, hand).isEmpty();
        if (hasBook) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world.isClient) return;
        PlayerEntity player = (PlayerEntity) user;
        Vec3d rot = player.getRotationVector();
        FireballEntity fireball = new FireballEntity(world, user, rot.x, rot.y, rot.z, 1);
        fireball.setPosition(player.getEyePos());
        world.spawnEntity(fireball);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) return stack;

        if (!(user instanceof PlayerEntity)) {
            throw new Error("UR FACE IS MAGICCC");
        }
        PlayerEntity player = (PlayerEntity) user;

        player.sendMessage(Text.literal("U went too far."));
        player.getItemCooldownManager().set(this, 10);
        return stack;
    }

    private ItemStack getSelectedBook(PlayerEntity player, Hand staffHand) {
        ItemStack stack = player.getStackInHand(staffHand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 150;
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