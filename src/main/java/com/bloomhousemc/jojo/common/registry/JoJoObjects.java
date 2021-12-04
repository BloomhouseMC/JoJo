package com.bloomhousemc.jojo.common.registry;

import com.bloomhousemc.jojo.common.item.KillerQueenTriggerItem;
import com.bloomhousemc.jojo.common.item.StandDiscItem;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.bloomhousemc.jojo.common.utils.JoJoUtils.*;

public class JoJoObjects {

    public static final Item ARROW_HEAD = register("arrow_head", new Item(gen()));
    public static final Item KILLER_QUEEN_TRIGGER = register("killer_queen_trigger", new KillerQueenTriggerItem(gen()));


    public static final Item STAR_PLATINUM_DISC = register("star_platinum_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item THE_WORLD_DISC = register("the_world_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item KILLER_QUEEN_DISC = register("killer_queen_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item CRAZY_DIAMOND_DISC = register("crazy_diamond_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item WEATHER_REPORT_DISC = register("weather_report_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item DARK_BLUE_MOON_DISC = register("dark_blue_moon_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item THE_SUN_DISC = register("the_sun_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item KING_CRIMSON_DISC = register("king_crimson_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item TWENTY_CENTURY_BOY_DISC = register("20th_century_boy_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item HIEROPHANT_GREEN_DISC = register("hierophant_green_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));

    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registry.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
        FLUIDS.keySet().forEach(fluid -> Registry.register(Registry.FLUID, FLUIDS.get(fluid), fluid));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }
}
