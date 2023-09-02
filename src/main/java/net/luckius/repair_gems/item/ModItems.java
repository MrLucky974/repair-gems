package net.luckius.repair_gems.item;

import net.luckius.luckiuslib.registration.ItemRegistryContainer;
import net.luckius.repair_gems.item.custom.RepairGemItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class ModItems extends ItemRegistryContainer {
    public static final Item WEAK_REPAIR_GEM = new RepairGemItem(200, 100, Rarity.COMMON);
    public static final Item BASIC_REPAIR_GEM = new RepairGemItem(150, 240, Rarity.COMMON);
    public static final Item STRONG_REPAIR_GEM = new RepairGemItem(100, 460, Rarity.UNCOMMON);
    public static final Item BETTER_REPAIR_GEM = new RepairGemItem(25, 750, Rarity.RARE);
    public static final Item ULTIMATE_REPAIR_GEM = new RepairGemItem(2, 975, Rarity.EPIC);
}
