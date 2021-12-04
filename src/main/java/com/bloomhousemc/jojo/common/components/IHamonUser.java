package com.bloomhousemc.jojo.common.components;

import com.bloomhousemc.jojo.common.hamon.Hamon;
import com.bloomhousemc.jojo.common.hamon.HamonMode;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.util.Identifier;

public interface IHamonUser extends ComponentV3, ServerTickingComponent {
    Hamon getHamon();
    void setHamon(Hamon value);
    boolean getHamonActive();
    void setHamonActive(boolean value);
    HamonMode getHamonMode();
    void setHamonMode(HamonMode value);
    int getHamonEnergy();
    void setHamonEnergy(int value);
    int getHamonLevel();
    void setHamonLevel(int value);

}