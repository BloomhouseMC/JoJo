package com.bloomhousemc.jojo.common.registry;

import com.bloomhousemc.jojo.JoJo;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class JoJoSoundEvents {
    private static final Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap();
    public static final SoundEvent THE_WORLD = create("stand.theworld");
    public static final SoundEvent THE_WORLD_END = create("stand.theworldend");
    public static final SoundEvent PUNCH = create("stand.punch");

    private static SoundEvent create(String name) {
        Identifier id = new Identifier(JoJo.MODID, name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

    public static void init() {
        SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
    }
}
