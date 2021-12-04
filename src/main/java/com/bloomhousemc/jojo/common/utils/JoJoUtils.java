package com.bloomhousemc.jojo.common.utils;

import com.bloomhousemc.jojo.JoJo;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class JoJoUtils {
    public static NbtCompound getTagCompoundSafe(ItemStack stack) {
        NbtCompound tagCompound = stack.getNbt();
        if (tagCompound == null) {
            tagCompound = new NbtCompound();
            stack.setNbt(tagCompound);
        }
        return tagCompound;
    }


    public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    public static final Map<FlowableFluid, Identifier> FLUIDS = new LinkedHashMap<>();
    public static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, new Identifier(JoJo.MODID, id));
        return type;
    }

    public static <T extends Block> T register(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(JoJo.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }

    public static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, new Identifier(JoJo.MODID, name));
        return item;
    }

    public static Item.Settings gen() {
        return new Item.Settings().group(JoJo.JOJO_GROUP);
    }
}
