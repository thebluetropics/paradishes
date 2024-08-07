package io.github.thebluetropics.paradishes;

import io.github.thebluetropics.paradishes.item.ParadishesItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParadishesMod implements ModInitializer {
  public static final String ID = "paradishes";
  public static final Logger LOGGER = LoggerFactory.getLogger(ID);

  @Override
  public void onInitialize() {
    ParadishesItems.initialize();

    LootTableEvents.MODIFY.register((key, builder, source, registries) -> {
      final var squidLootTableKey = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/squid"));

      if (key.equals(squidLootTableKey)) {
        LootPool pool = LootPool.builder()
          .rolls(ConstantLootNumberProvider.create(1f))
          .with(ItemEntry.builder(ParadishesItems.RAW_CALAMARI))
          .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)))
          .build();

        builder.pool(pool);
      }
    });
  }
}
