package net.steveson.greedydeployers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = CreateGreedyDeployersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue GRAB_MORE_VAULTS = BUILDER
            .comment("Whether deployer should maintain a higher stack size of vault and similar items.")
            .define("grabMoreVaults", true);

    private static final ForgeConfigSpec.IntValue VAULTLIKE_STACK_SIZE = BUILDER
            .comment("What stack size deployers should attempt to maintain for items that place like vaults")
            .defineInRange("vaultlikeStackSize", 10, 9, 10);

    private static final ForgeConfigSpec.IntValue CUSTOM_STACK_SIZE_SMALL = BUILDER
            .comment("What stack size deployers should attempt to maintain for tagged items")
            .defineInRange("smallCustomStackSize", 6, 1, 16);

    private static final ForgeConfigSpec.IntValue CUSTOM_STACK_SIZE_MEDIUM = BUILDER
            .comment("What stack size deployers should attempt to maintain for tagged items")
            .defineInRange("mediumCustomStackSize", 16, 2, 32);

    private static final ForgeConfigSpec.IntValue CUSTOM_STACK_SIZE_LARGE = BUILDER
            .comment("What stack size deployers should attempt to maintain for tagged items")
            .defineInRange("largeCustomStackSize", 64, 3, 64);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int desiredStackSizeSmall;
    public static int desiredStackSizeVaultlike;
    public static int desiredStackSizeMedium;
    public static int desiredStackSizeLarge;
    public static boolean grabVaults;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        desiredStackSizeSmall = CUSTOM_STACK_SIZE_SMALL.get();
        desiredStackSizeVaultlike = VAULTLIKE_STACK_SIZE.get();
        desiredStackSizeMedium = CUSTOM_STACK_SIZE_MEDIUM.get();
        desiredStackSizeLarge = CUSTOM_STACK_SIZE_LARGE.get();
        grabVaults = GRAB_MORE_VAULTS.get();
    }

}
