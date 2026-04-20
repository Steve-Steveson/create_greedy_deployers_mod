package net.steveson.greedydeployers.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.steveson.greedydeployers.CreateGreedyDeployersMod;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> DEPLOYER_WANTS_MORE = tag("deployer_grabs_extra");

        //This tag flags items to which the added behavior should apply
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CreateGreedyDeployersMod.MOD_ID, name));
        }
    }
}
