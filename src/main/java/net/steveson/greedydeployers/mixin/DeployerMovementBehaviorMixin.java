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
        if (filter.item().is(ModTags.Items.DEPLOYER_WANTS_MORE)) {

            ItemStack itemStack = player.getMainHandItem();
            int desire = Config.desiredStackSize - itemStack.getCount();

            if (ItemStack.isSameItemSameTags(filter.item(), itemStack) && desire > 0) {

                ItemStack held = ItemHelper.extract(context.contraption.getStorage().getAllItems(),
                        stack -> filter.test(context.world, stack), desire, false);

                itemStack.setCount(itemStack.getCount() + held.getCount());
            }
        }
    }
}
