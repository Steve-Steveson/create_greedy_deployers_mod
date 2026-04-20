package net.steveson.greedydeployers.mixin;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import com.simibubi.create.content.kinetics.deployer.DeployerMovementBehaviour;
import com.simibubi.create.content.logistics.filter.FilterItemStack;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.steveson.greedydeployers.Config;
import net.steveson.greedydeployers.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.commands.arguments.EntityArgument.getPlayer;

@Mixin(DeployerMovementBehaviour.class)
public class DeployerMovementBehaviorMixin {


    @Inject(method = "tryGrabbingItem", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void tryGrabbingItem(MovementContext context, CallbackInfo ci, DeployerFakePlayer player) {
        FilterItemStack filter = context.getFilterFromBE();
        if (filter.item().is(ModTags.Items.DEPLOYER_WANTS_MORE)) {
//            System.out.println("I've got a " + filter.item());

            ItemStack itemStack = player.getMainHandItem();
            int desire = Config.desiredStackSize - itemStack.getCount();

            if (!itemStack.isEmpty() && desire > 0) {
                System.out.println("give me " + desire);

                ItemStack held = ItemHelper.extract(context.contraption.getStorage().getAllItems(),
                        stack -> filter.test(context.world, stack), desire, false);


                itemStack.setCount(itemStack.getCount() + held.getCount());

//                player.setItemInHand(InteractionHand.MAIN_HAND, held);
            }
        }
    }

//    @Overwrite
//    private void tryGrabbingItem(MovementContext context) {
//        DeployerFakePlayer player = getPlayer(context);
//
//        System.out.println("mixing in lol");
//
//        if (player == null)
//            return;
//        if (player.getMainHandItem()
//                .isEmpty()) {
//            FilterItemStack filter = context.getFilterFromBE();
//            if (AllItems.SCHEMATIC.isIn(filter.item()))
//                return;
//            ItemStack held = ItemHelper.extract(context.contraption.getStorage().getAllItems(),
//                    stack -> filter.test(context.world, stack), 1, false);
//            player.setItemInHand(InteractionHand.MAIN_HAND, held);
//        }
//    }

}
