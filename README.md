# TreeFeller

TreeFeller is a customisable server side mod built on top of [BFAPI](https://github.com/BradBot1/BradsFabricApi) that allows for trees to be fully felled from the breaking of one block

## Required Mods

> This mod is dependent on [BFAPI](https://github.com/BradBot1/BradsFabricApi)

## Suggested Mods *(optional)*

> [FastDecay](https://github.com/BradBot1/FastDecay)

## Config

The config is found under `treefeller.json` in the config directory

|Field|Type|Description|Default|
|-----|----|-----------|-------|
|maxFellingSize|Integer|The maximum size of fellable blocks the mod will break before stopping|100|
|registerAllAxesAsFellableItems|Boolean|If all axes should be automatically added to the fellableItems array|true|
|fellableItems|Array|Identifiers of the items that can be used to fell fellableBlocks|N/A|
|fellableBlocks|Array|Identifiers of the blocks that can be felled|N/A|

```json
{
  "maxFellingSize": 100,
  "fellableBlocks": [
    "minecraft:oak_log",
    "minecraft:spruce_log",
    "minecraft:birch_log",
    "minecraft:jungle_log",
    "minecraft:acacia_log",
    "minecraft:dark_oak_log",
    "minecraft:warped_stem",
    "minecraft:crimson_stem"
  ],
  "registerAllAxesAsFellableItems": true,
  "fellableItems": []
}
```

## Links

* [GitHub](https://github.com/BradBot1/TreeFeller)
* [ModRinth](https://modrinth.com/mod/treefeller)
* [CurseForge](https://www.curseforge.com/minecraft/mc-mods/treefeller)