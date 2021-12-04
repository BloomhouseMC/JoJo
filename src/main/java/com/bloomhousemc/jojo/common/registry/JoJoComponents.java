package com.bloomhousemc.jojo.common.registry;

import com.bloomhousemc.jojo.JoJo;
import com.bloomhousemc.jojo.common.components.IStandUser;
import com.bloomhousemc.jojo.common.components.ITimeStop;
import com.bloomhousemc.jojo.common.components.StandComponent;
import com.bloomhousemc.jojo.common.components.TimeStopComponent;
import com.bloomhousemc.jojo.common.components.HamonComponent;
import com.bloomhousemc.jojo.common.components.IHamonUser;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class JoJoComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public static final ComponentKey<IStandUser> STAND_USER_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(JoJo.MODID, "stand_user"), IStandUser.class);
    public static final ComponentKey<IHamonUser> HAMON_USER_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(JoJo.MODID, "hamon_user"), IHamonUser.class);
    public static final ComponentKey<ITimeStop> TIME_STOP_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(JoJo.MODID,"time_stop"), ITimeStop.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, STAND_USER_COMPONENT).impl(StandComponent.class).end(StandComponent::new);
        registry.beginRegistration(PlayerEntity.class, HAMON_USER_COMPONENT).impl(HamonComponent.class).end(HamonComponent::new);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(TIME_STOP_COMPONENT, TimeStopComponent.class, TimeStopComponent::new);
    }
}
