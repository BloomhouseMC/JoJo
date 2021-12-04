package com.bloomhousemc.jojo.common.enchantment;

import com.bloomhousemc.jojo.common.registry.JoJoEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class StandEnchantment extends Enchantment {
    //public static String calc;

    public StandEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other)
        && other != JoJoEnchantments.CRAZY_DIAMOND_ENCHANTMENT
        && other != JoJoEnchantments.THE_SUN_ENCHANTMENT
        && other != JoJoEnchantments.STAR_PLATINUM_ENCHANTMENT
        && other != JoJoEnchantments.KILLER_QUEEN_ENCHANTMENT
        && other != JoJoEnchantments.DARK_BLUE_MOON_ENCHANTMENT
        && other != JoJoEnchantments.KING_CRIMSON_ENCHANTMENT
        && other != JoJoEnchantments.PURPLE_HAZE_ENCHANTMENT
        && other != JoJoEnchantments.THE_WORLD_ENCHANTMENT
        && other != JoJoEnchantments.TWENTY_CENTURY_BOY_ENCHANTMENT
        && other != JoJoEnchantments.WEATHER_REPORT_ENCHANTMENT
        && other != JoJoEnchantments.HIEROPHANT_GREEN_ENCHANTMENT
        && other != JoJoEnchantments.ANUBIS_ENCHANTMENT;
    }

    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return super.isAcceptableItem(stack);
    }
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }


}