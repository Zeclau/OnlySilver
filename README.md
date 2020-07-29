
OnlySilver
==========

A mod which primarily implements the old silver from SimpleOres. New features may be added for better overall experience or mod compatibility.

## Add-ons and Compatibility

OnlySilver for 1.14.4/1.15.2 and later is an add-on for SimpleOres, with
a required dependency of SimpleOres.

## Features

* Base Items and Blocks

  * Ore
  * Ingot
  * (Compressed) Block
  * Rod
    * Cost two ingots each

* Material Definitions

  * Tool Parameters
  * Armor Parameters

* Equipment

  * Vanilla Tool Set: Pickaxe, Axe, Shovel, Sword and Hoe
  * Vanilla Armor Set: Helmet, Chestplate, Leggings and Boots
  * Silver Bow
    * Dual ranged and melee knockback effect
    * Material logic applied: enchantability, repairing material, etc.
    * Crafted from rods, strings and an iron ingot

* Enchantments

  * Incantation
    * Silver tools only
    * "Incant" mob drops
    * Export Silver Aura to non-silver tools
  * Silver Aura
    * Obtained at enchanting table only with silver items
    * All-round boosts proportional to enchantability
    * Item entity invulnerability except to void damage

* Mobs

  * Silver Golem
    * Tiny and fast moving
    * Immune to poison
    * Crafted from a silver block and a pumpkin / lantern
      * Dispenser support is *mandatory*
  * Skeleton Compatibility for Silver Bow
    * HIGHLY UNLIKELY THIS WILL BE IMPLEMENTED.
    * Enable them to shoot with silver bows
    * Dedicated handling of ranged knockback effect
      * Silver Aura boosts maybe handled explicitly as well
  * Equipment Spawning on Mobs in Vanilla Fashion
    * HIGHLY UNLIKELY THIS WILL BE IMPLEMENTED.
    * Armor on any `EntityLiving` that calls `setEquipmentBasedOnDifficulty` on its spawn
    * A Sword or a bow on skeletons

* Ore Generation

  * Customizable in common Options.

* Mod Options

  * customizable ore generation parameters.
  * ????

## LICENSE

  * Zot201's original mod was licensed under Apache License 2.0.
  * Sinhika's port has been re-licensed under the compatible Lesser GNU Public License 3.0, found in the file 'lgpl.txt' in this repository.
