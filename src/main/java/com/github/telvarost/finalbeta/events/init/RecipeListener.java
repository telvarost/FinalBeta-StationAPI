package com.github.telvarost.finalbeta.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener(priority = ListenerPriority.LOWEST)
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.WOOL.asItem(), 1), new ItemInstance(BlockBase.WOOL.asItem(), 1, -1), new ItemInstance(ItemBase.dyePowder, 1, 15));
        }
    }
}