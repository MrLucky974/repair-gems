![Repair Gems Title](https://i.imgur.com/bvIxc8p.png)


# 💎 Repair Gems 💎

A simple mod that adds a new item called a Repair Gem that undo the damage on your items. An alternative to Mending !

The gems work while in inventory. Each level decreases cooldown and increases durability.

## Gems :

- Weak Repair Gem :
  - Cooldown : 200 ticks (= 10 seconds)
  - Durability : 100
  - Recipe : ![](https://i.imgur.com/AS5ZxKW.png)
- Basic Repair Gem :
  - Cooldown : 150 ticks (= 7.5 seconds)
  - Durability : 240
  - Recipe : ![](https://i.imgur.com/MAgqAmU.png)
- Strong Repair Gem :
  - Cooldown : 100 ticks (= 5 seconds)
  - Durability : 460
  - Recipe : ![](https://i.imgur.com/cpUd895.png)
- Better Repair Gem :
  - Cooldown : 25 ticks (= 1.25 seconds)
  - Durability : 750
  - Recipe : ![](https://i.imgur.com/RADLlpj.png)
- Ultimate Repair Gem :
  - Cooldown : 2 ticks (= 0.1 seconds)
  - Durability : 975
  - Recipe : ![](https://i.imgur.com/wrUtnhS.png)

## Customization :

### *Options* :

- *unbreakable (default = true)* : By default, repair gems are unbreakable meaning they don't use durability. If disabled, repair gems can be broken.
- *repairSingleItem (default = true)* : If enabled, repair gems only repair one item before activating cooldown. Else, it repairs every damaged item in the inventory.
- *repairOtherGems (default = false)* : Determine if repair gems are able to repair other repair gems.

### *For Users* :

You can change some parameters in the ``repair_gems_config.json5`` file located in ``%appdata/.minecraft/config/``.
It is also possible to change the parameters in-game using [ModMenu](https://modrinth.com/mod/modmenu/) or by typing the command ``/owo-config repair_gems``.

### *For Mod/Modpack Creators* :

You can prevent specific items from being repaired by Repair Gems,
by adding them to a ``data/repair_gems/tags/items/excluded_from_repair.json`` file.

**Note for devs** : If you have Data Generation, you can add this to your ``ItemTagProvider``:

```java
public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
  // This is a reference to the tag location, you can use it in code with stack.isIn(ItemTagProvider.EXCLUDED_FROM_REPAIR);
  public static final TagKey<Item> EXCLUDED_FROM_REPAIR = TagKey.of(RegistryKeys.ITEM, new Identifier("repair_gems:excluded_from_repair"));

  public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
    super(output, completableFuture);
  }

  @Override
  protected void configure(RegistryWrapper.WrapperLookup arg) {
    // This is where you add items to the tag.
    getOrCreateTagBuilder(EXCLUDED_FROM_REPAIR)
            .add(ModItems.EXAMPLE_ITEM);
  }
}
```