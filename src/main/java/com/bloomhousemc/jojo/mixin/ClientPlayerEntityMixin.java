package com.bloomhousemc.jojo.mixin;

import com.bloomhousemc.jojo.common.stand.Stand;
import com.bloomhousemc.jojo.common.stand.StandUtils;
import com.bloomhousemc.jojo.common.timestop.TimeStopUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
    protected void doNotLetPlayersMoveWhenTimeIsStopped(CallbackInfo ci) {
        if (TimeStopUtils.getTimeStoppedTicks(world) > 0 && StandUtils.getStand(this) != Stand.THE_WORLD && StandUtils.getStand(this) != Stand.STAR_PLATINUM && TimeStopUtils.isInRangeOfTimeStop(this)) {
            ci.cancel();
        }
    }

}
