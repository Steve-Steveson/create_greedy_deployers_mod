package net.steveson.greedydeployers.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.steveson.greedydeployers.CreateGreedyDeployersMod;

public class ModTags {

    public static class Items {
        //These tags are split because deployer on contraptions don't place tanks in 3x3 regardless of amount in hand.
        //  If that were to be fixed, players could easily add the places_like_tanks tag to the deployer_grabs_vaultlike_amount tag.
        public static final TagKey<Item> VAULTLIKE_ITEMS = tag("places_like_vaults");
        public static final TagKey<Item> TANKLIKE_ITEMS = tag("places_like_tanks");

        public static final TagKey<Item> DEPLOYER_WANTS_VAULTLIKE_AMOUNT = tag("deployer_grabs_vaultlike_amount");
        public static final TagKey<Item> DEPLOYER_WANTS_SMALL_AMOUNT = tag("deployer_grabs_small_amount");
        public static final TagKey<Item> DEPLOYER_WANTS_MEDIUM_AMOUNT = tag("deployer_grabs_medium_amount");
        public static final TagKey<Item> DEPLOYER_WANTS_LARGE_AMOUNT = tag("deployer_grabs_large_amount");

        //This tag flags items to which the added behavior should apply
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CreateGreedyDeployersMod.MOD_ID, name));
        }
    }
}
