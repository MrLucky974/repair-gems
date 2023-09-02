package net.luckius.repair_gems.item.custom;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.luckius.luckiuslib.util.TooltipHelper;
import net.luckius.repair_gems.RepairGems;
import net.luckius.repair_gems.data.tag.ItemTagProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RepairGemItem extends TrinketItem {
    private final int cooldown;

    public RepairGemItem(int cooldown, int durability, Rarity rarity) {
        super(new OwoItemSettings()
                .group(RepairGems.MAIN_GROUP)
                .maxCount(1)
                .rarity(rarity)
                .maxDamage(RepairGems.CONFIG.unbreakable() ? -1 : durability)
        );

        this.cooldown = cooldown;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        TooltipHelper.expandableTooltip(tooltip,
                Text.translatable("tooltip.repair_gems.repair_gem.expanded_tooltip", ((float)this.cooldown / 20.0f)).formatted(Formatting.ITALIC, Formatting.DARK_PURPLE)
        );
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) return;
        if (!(entity instanceof PlayerEntity playerEntity)) return;

        float cooldown = playerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), 0.0F);
        if (cooldown <= 0) {
            if (repair(playerEntity)) {
                if (stack.isDamageable()) stack.setDamage(1); // Damage the gem.
            }
            playerEntity.getItemCooldownManager().set(stack.getItem(), this.cooldown);
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        World world = entity.getWorld();

        if (world.isClient) return;
        if (!(entity instanceof PlayerEntity playerEntity)) return;

        float cooldown = playerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), 0.0F);
        if (cooldown <= 0) {
            if (repair(playerEntity)) {
                if (stack.isDamageable()) stack.setDamage(1); // Damage the gem.
            }
            playerEntity.getItemCooldownManager().set(stack.getItem(), this.cooldown);
        }
    }

    private static boolean repair(PlayerEntity player) {
        PlayerInventory inventory = player.getInventory();
        boolean flag = false;

        // Scan the player's inventory.
        for (int slotId = 0; slotId < inventory.size(); slotId++) {
            ItemStack stack = inventory.getStack(slotId);

            boolean canRepairGems = (!RepairGems.CONFIG.unbreakable() && RepairGems.CONFIG.repairOtherGems());
            if ((stack.getItem() instanceof RepairGemItem && !canRepairGems) || stack.isIn(ItemTagProvider.EXCLUDED_FROM_REPAIR)) continue;

            if (!stack.isEmpty() && stack.getItem().isDamageable()) {
                // Do not repair if holding & using.
                if (player.handSwinging && stack == player.getStackInHand(Hand.MAIN_HAND)) continue;

                // Repair.
                if (stack.isDamaged()) {
                    stack.setDamage(stack.getDamage() - 1);
                    flag = true;
                    if (RepairGems.CONFIG.repairSingleItem()) return true; // Only repair one item at a time.
                }
            }
        }

        return flag;
    }
}
