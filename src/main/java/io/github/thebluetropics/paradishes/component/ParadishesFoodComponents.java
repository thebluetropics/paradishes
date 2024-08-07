package io.github.thebluetropics.paradishes.component;

import net.minecraft.component.type.FoodComponent;

public class ParadishesFoodComponents {
  public static final FoodComponent RAW_CALAMARI = new FoodComponent.Builder()
    .nutrition(2)
    .saturationModifier(0.1f)
    .build();
  public static final FoodComponent COOKED_CALAMARI = new FoodComponent.Builder()
    .nutrition(5)
    .saturationModifier(0.6f)
    .build();
}
