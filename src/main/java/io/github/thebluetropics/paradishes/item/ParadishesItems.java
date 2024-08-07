package io.github.thebluetropics.paradishes.item;

import io.github.thebluetropics.paradishes.ParadishesMod;
import io.github.thebluetropics.paradishes.component.ParadishesFoodComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParadishesItems {
  public static final Item RAW_CALAMARI = register(
    "raw_calamari",
    new Item(
      new Item.Settings()
        .food(ParadishesFoodComponents.RAW_CALAMARI)
    )
  );
  public static final Item COOKED_CALAMARI = register(
    "cooked_calamari",
    new Item(
      new Item.Settings()
        .food(ParadishesFoodComponents.COOKED_CALAMARI)
    )
  );

  public static <T extends Item> T register(String id, T item) {
    return Registry.register(Registries.ITEM, Identifier.of(ParadishesMod.ID, id), item);
  }

  public static void initialize() { /* ... */ }
}
