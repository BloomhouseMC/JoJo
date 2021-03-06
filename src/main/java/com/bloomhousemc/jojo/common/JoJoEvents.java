package com.bloomhousemc.jojo.common;

import com.bloomhousemc.jojo.common.item.KillerQueenTriggerItem;
import com.bloomhousemc.jojo.common.registry.JoJoObjects;
import com.bloomhousemc.jojo.common.stand.Stand;
import com.bloomhousemc.jojo.common.stand.StandUtils;
import com.bloomhousemc.jojo.common.utils.JoJoUtils;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JoJoEvents implements AttackEntityCallback, AttackBlockCallback {


    public static void init(){
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(StandUtils.getStand(player) == Stand.KILLER_QUEEN && hand == Hand.MAIN_HAND){
                if(!player.getInventory().contains(new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER))){
                    ItemStack trigger = new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER);
                    KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.ENTITY.getName(), entity.getUuid().toString(), 0, 0, 0);
                    if(player.getStackInHand(Hand.MAIN_HAND).isEmpty()){
                        player.setStackInHand(Hand.MAIN_HAND, trigger);
                    }else{
                        player.dropItem(trigger, false, true);
                    }
                }else{
                    PlayerInventory inventory = player.getInventory();
                    List<ItemStack> mainInventory = inventory.main;
                    for(ItemStack trigger : mainInventory) {
                        if(trigger.getItem() == JoJoObjects.KILLER_QUEEN_TRIGGER) {
                            KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.ENTITY.getName(), entity.getUuid().toString(), 0, 0, 0);
                            break;
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if(StandUtils.getStand(player) == Stand.KILLER_QUEEN && hand == Hand.MAIN_HAND) {
                if(!player.getInventory().contains(new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER))){
                    HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;

                    float newPosX = pos.getX();
                    float newPosY = pos.getY();
                    float newPosZ = pos.getZ();

                    if (hitResult instanceof BlockHitResult) {
                        direction = ((BlockHitResult) hitResult).getSide();
                        switch (direction) {
                            case UP:
                                newPosY = pos.getY() + 1.0F;
                                break;
                            case DOWN:
                                newPosY = pos.getY() - 1.0F;
                                break;
                            case NORTH:
                                newPosX = pos.getX() + 1.0F;
                                break;
                            case SOUTH:
                                newPosX = pos.getX() - 1.0F;
                                break;
                            case EAST:
                                newPosZ = pos.getZ() + 1.0F;
                                break;
                            case WEST:
                                newPosZ = pos.getZ() - 1.0F;
                                break;
                            default:
                        }
                    }

                    ItemStack trigger = new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER);
                    KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.BLOCK.getName(),"empty",newPosX, newPosY, newPosZ);

                    if(player.getStackInHand(Hand.MAIN_HAND).isEmpty()){
                        player.setStackInHand(Hand.MAIN_HAND, trigger);
                    }else{
                        player.dropItem(trigger, false, true);
                    }
                }else{
                    PlayerInventory inventory = player.getInventory();
                    List<ItemStack> mainInventory = inventory.main;
                    for(ItemStack trigger : mainInventory) {
                        if(trigger.getItem() == JoJoObjects.KILLER_QUEEN_TRIGGER) {
                            KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.BLOCK.getName(),"empty",pos.getX(), pos.getY(), pos.getZ());
                            break;
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });
    }


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        return null;
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        return null;
    }
}
