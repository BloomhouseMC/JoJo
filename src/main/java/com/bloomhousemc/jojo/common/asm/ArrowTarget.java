package com.bloomhousemc.jojo.common.asm;

import com.bloomhousemc.jojo.common.item.ArrowHeadItem;
import com.bloomhousemc.jojo.mixin.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class ArrowTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof ArrowHeadItem;
    }
}