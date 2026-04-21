package net.steveson.greedydeployers.mixin;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import com.simibubi.create.content.kinetics.deployer.DeployerMovementBehaviour;
import com.simibubi.create.content.logistics.filter.FilterItemStack;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.world.item.ItemStack;
import net.steveson.greedydeployers.Config;
import net.steveson.greedydeployers.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DeployerMovementBehaviour.class)
public class DeployerMovementBehaviorMixin {

    @Inject(method = "tryGrabbingItem", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    private void tryGrabbingItem(MovementContext context, CallbackInfo ci, DeployerFakePlayer player) {
        FilterItemStack filter = context.getFilterFromBE();
        //Checking the filter item for the correct tag is done first so that other items don't run unnecessary code.
        int target = 1;
        if (Config.grabVaults && filter.item().is(ModTags.Items.DEPLOYER_WANTS_VAULTLIKE_AMOUNT)) {
            target = Config.desiredStackSizeVaultlike;
        } else
        if (filter.item().is(ModTags.Items.DEPLOYER_WANTS_LARGE_AMOUNT)) {
            target = Config.desiredStackSizeMedium;
        } else
        if (filter.item().is(ModTags.Items.DEPLOYER_WANTS_MEDIUM_AMOUNT)) {
            target = Config.desiredStackSizeMedium;
        } else
        if (filter.item().is(ModTags.Items.DEPLOYER_WANTS_SMALL_AMOUNT)) {
            target = Config.desiredStackSizeSmall;
        }


        if (target > 1) {
            ItemStack itemStack = player.getMainHandItem();
            //determines how many items need to be grabbed to reach the number specified by the config file
            int desire = target - itemStack.getCount();

            //ignore cases where held item cannot stack with filter item, or it has enough items already
            if (ItemStack.isSameItemSameTags(filter.item(), itemStack) && desire > 0) {

                //remove desired amount of the filtered item from attached inventories
                //  will not grab if it cannot reach the desired amount
                ItemStack held = ItemHelper.extract(context.contraption.getStorage().getAllItems(),
                        stack -> filter.test(context.world, stack), desire, false);

                //and adds that number of items to the stack in the deployer
                itemStack.setCount(itemStack.getCount() + held.getCount());
            }
        }
    }
}
