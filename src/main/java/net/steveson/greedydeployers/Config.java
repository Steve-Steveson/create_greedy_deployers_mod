package net.steveson.greedydeployers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = CreateGreedyDeployersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

//    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);

    private static final ForgeConfigSpec.IntValue DESIRED_STACK_SIZE = BUILDER
            .comment("What stack size deployers should attempt to maintain for tagged items")
            .defineInRange("stackSize", 6, 1, 64);


    static final ForgeConfigSpec SPEC = BUILDER.build();

//    public static boolean logDirtBlock;
    public static int desiredStackSize;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
//        logDirtBlock = LOG_DIRT_BLOCK.get();
        desiredStackSize = DESIRED_STACK_SIZE.get();


    }
}
