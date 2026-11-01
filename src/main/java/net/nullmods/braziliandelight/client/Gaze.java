package net.nullmods.braziliandelight.client;

import it.unimi.dsi.fastutil.HashCommon;

public enum Gaze {
    FORWARD,
    RIGHT;

    private static final long SALT = 0x5DEECE66DL;
    private static final int MIN_PERIOD = 240;
    private static final int PERIOD_SPREAD = 360;
    private static final int MIN_LOOK = 30;
    private static final int LOOK_SPREAD = 40;

    public static Gaze at(long seed, long gameTime) {
        long hash = HashCommon.mix(seed ^ SALT);
        int period = MIN_PERIOD + (int) Math.floorMod(hash, (long) PERIOD_SPREAD);
        long t = gameTime + Math.floorMod(hash >>> 20, (long) period);
        long cycle = Math.floorDiv(t, period);
        int look = MIN_LOOK + (int) Math.floorMod(HashCommon.mix(hash ^ cycle), (long) LOOK_SPREAD);
        return Math.floorMod(t, period) < look ? RIGHT : FORWARD;
    }
}
