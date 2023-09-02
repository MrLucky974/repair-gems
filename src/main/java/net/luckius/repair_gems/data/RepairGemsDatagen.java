package net.luckius.repair_gems.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.luckius.repair_gems.data.tag.ItemTagProvider;

public class RepairGemsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Tag Providers
        pack.addProvider(ItemTagProvider::new);
    }
}