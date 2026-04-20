package net.steveson.greedydeployers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = CreateGreedyDeployersMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.IntValue DESIRED_STACK_SIZE = BUILDER
            .comment("What stack size deployers should attempt to maintain for tagged items")
            .defineInRange("stackSize", 6, 1, 64);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int desiredStackSize;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        desiredStackSize = DESIRED_STACK_SIZE.get();
    }
}
