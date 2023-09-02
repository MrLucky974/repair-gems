package net.luckius.repair_gems;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.luckius.luckiuslib.registration.FieldRegistrationHandler;
import net.luckius.repair_gems.config.ModConfig;
import net.luckius.repair_gems.item.ModItems;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RepairGems implements ModInitializer {
    public static final String MOD_ID = "repair_gems";
    public static final Logger LOGGER = LoggerFactory.getLogger("Repair Gems");
    public static final ModConfig CONFIG = ModConfig.createAndLoad();

    public static final OwoItemGroup MAIN_GROUP = OwoItemGroup
            .builder(new Identifier(MOD_ID, "repair_gems"), () -> Icon.of(ModItems.ULTIMATE_REPAIR_GEM))
            .build();

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world from Repair Gems!");

        MAIN_GROUP.initialize();

        FieldRegistrationHandler.register(ModItems.class, MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.add(ModItems.WEAK_REPAIR_GEM);
            content.add(ModItems.BASIC_REPAIR_GEM);
            content.add(ModItems.STRONG_REPAIR_GEM);
            content.add(ModItems.BETTER_REPAIR_GEM);
            content.add(ModItems.ULTIMATE_REPAIR_GEM);
        });
    }
}
