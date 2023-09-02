package net.luckius.repair_gems.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;
import net.luckius.repair_gems.RepairGems;

@Modmenu(modId = RepairGems.MOD_ID)
@Config(name = "repair_gems_config", wrapperName = "ModConfig")
public class ModConfigModel {
    @RestartRequired
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean unbreakable = true;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean repairSingleItem = true;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean repairOtherGems = false;
}