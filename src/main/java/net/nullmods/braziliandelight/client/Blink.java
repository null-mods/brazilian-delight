package net.nullmods.braziliandelight.client;

import it.unimi.dsi.fastutil.HashCommon;

public enum Blink {
    OPEN,
    HALF,
    CLOSED;

    private static final int MIN_PERIOD = 50;
    private static final int PERIOD_SPREAD = 90;
    private static final float BLINK_LENGTH = 6.0F;
    private static final float SECOND_BLINK_DELAY = 8.0F;

    public static Blink at(long seed, long gameTime, float partialTick) {
        long hash = HashCommon.mix(seed);
        int period = MIN_PERIOD + (int) Math.floorMod(hash, (long) PERIOD_SPREAD);
        long t = gameTime + Math.floorMod(hash >>> 16, (long) period);
        long cycle = Math.floorDiv(t, period);
        float phase = Math.floorMod(t, period) + partialTick;
        if (phase < BLINK_LENGTH) {
            return lid(phase);
        }
        boolean doubleBlink = Math.floorMod(HashCommon.mix(hash ^ cycle), 4L) == 0;
        if (doubleBlink && phase >= SECOND_BLINK_DELAY && phase < SECOND_BLINK_DELAY + BLINK_LENGTH) {
            return lid(phase - SECOND_BLINK_DELAY);
        }
        return OPEN;
    }

    private static Blink lid(float phase) {
        if (phase < 1.5F || phase >= 4.5F) {
            return HALF;
        }
        return CLOSED;
    }
}
