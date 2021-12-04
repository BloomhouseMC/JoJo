package com.bloomhousemc.jojo.common.components;

import com.bloomhousemc.jojo.common.stand.Stand;
import com.bloomhousemc.jojo.common.stand.StandMode;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.util.Identifier;

public interface IStandUser extends ComponentV3, ServerTickingComponent {
    Stand getStand();
    void setStand(Stand value);
    boolean getStandActive();
    void setStandActive(boolean value);
    StandMode getStandMode();
    void setStandMode(StandMode value);
    int getStandEnergy();
    void setStandEnergy(int value);
    int getStandLevel();
    void setStandLevel(int value);


}