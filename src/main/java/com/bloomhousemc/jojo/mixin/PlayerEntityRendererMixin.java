package com.bloomhousemc.jojo.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }



    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;Z)V", at = @At("TAIL"))
    private void PlayerEntityRenderer(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        //TODO
        /*
        addFeature(new TwentyCenturyBoyFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new TheWorldFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new TheSunFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new StarPlatinumFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new KillerQueenFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new HierophantGreenFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new CrazyDiamondFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new PurpleHazeFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new DarkBlueMoonFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new WeatherReportFeatureRenderer(this, ctx.getModelLoader()));
        addFeature(new KingCrimsonFeatureRenderer(this, ctx.getModelLoader()));

         */

    }
}
