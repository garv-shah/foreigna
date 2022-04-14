package com.garv.foreigna.item;

import com.garv.foreigna.Foreigna;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item BEAN = registerItem("bean",
            new Item(
                    new FabricItemSettings()
                            .group(ItemGroup.MISC)
            )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Foreigna.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Foreigna.LOGGER.info("Registering Mod Items for " + Foreigna.MOD_ID);
    }

}
