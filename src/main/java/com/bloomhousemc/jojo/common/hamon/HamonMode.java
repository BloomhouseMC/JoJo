package com.bloomhousemc.jojo.common.hamon;

import java.util.Locale;

public enum HamonMode {
    IDLE;

    @Override
    public String toString() {
        return "status.hamon."+name().toLowerCase(Locale.ROOT);
    }
}