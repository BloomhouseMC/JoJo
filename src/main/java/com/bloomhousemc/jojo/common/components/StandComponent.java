package com.bloomhousemc.jojo.common.components;

import com.bloomhousemc.jojo.common.registry.JoJoComponents;
import com.bloomhousemc.jojo.common.stand.Stand;
import com.bloomhousemc.jojo.common.stand.StandMode;
import com.bloomhousemc.jojo.common.stand.StandUtils;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class StandComponent implements IStandUser, AutoSyncedComponent, PlayerComponent<StandComponent> {
    private PlayerEntity player;
    private Stand stand = Stand.NONE;
    private boolean standActive = false;
    private StandMode standMode = StandMode.IDLE;
    private int standEnergy = 100000;
    private int standLevel = 0;
    public StandComponent(PlayerEntity playerEntity) {
        this.player = playerEntity;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        try {
            stand = Stand.valueOf(tag.getString("StandType"));
        } catch (IllegalArgumentException e) {
            stand = Stand.NONE;
        }
        standActive = tag.getBoolean("StandActive");
        standMode = StandMode.values()[tag.getByte("StandMode")];
        standEnergy = tag.getInt("StandEnergy");
        standLevel = tag.getInt("StandLevel");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("StandType", stand.name());
        tag.putBoolean("StandActive", standActive);
        tag.putByte("StandMode", (byte) standMode.ordinal());
        tag.putInt("StandEnergy", standEnergy);
        tag.putInt("StandLevel", standLevel);
    }

    @Override
    public Stand getStand() {
        return stand;
    }

    @Override
    public void setStand(Stand stand) {
        this.stand = stand;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public boolean getStandActive() {
        return standActive;
    }

    @Override
    public void setStandActive(boolean standActive) {
        this.standActive = standActive;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public StandMode getStandMode() {
        return standMode;
    }

    @Override
    public void setStandMode(StandMode standMode) {
        this.standMode = standMode;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public int getStandEnergy() {
        return standEnergy;
    }

    @Override
    public void setStandEnergy(int standEnergy) {
        this.standEnergy = standEnergy;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public int getStandLevel() {
        return standLevel;
    }

    @Override
    public void setStandLevel(int standLevel) {
        this.standLevel = standLevel;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public void serverTick() {
        standEnergy = Math.min(standEnergy+100, 100000);
        if(standActive && standMode.equals(StandMode.ATTACKING)){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 2));
        }
        if(standActive && standMode.equals(StandMode.HEALING)){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40, 2));
        }
        if(stand != Stand.CRAZY_DIAMOND && standMode == StandMode.HEALING){
            StandUtils.setStandMode(player, StandMode.IDLE);
        }
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }
    @Override
    public void copyForRespawn(StandComponent original, boolean lossless, boolean keepInventory, boolean sameCharacter) {
        if (lossless) {
            copyFrom(original);
        } else {
            stand = original.stand;
            standLevel = original.standLevel;
            JoJoComponents.STAND_USER_COMPONENT.sync(player);
        }
    }

    @Override
    public void copyFrom(StandComponent other) {
        stand = other.stand;
        standActive = other.standActive;
        standMode = other.standMode;
        standEnergy = other.standEnergy;
        standLevel = other.standLevel;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }
}
