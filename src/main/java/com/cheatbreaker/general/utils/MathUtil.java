/**
 * The LGPL License
 * <p>
 * Copyright (C) 2019-2025 CheatBreaker.net
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.cheatbreaker.general.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public final class MathUtil {
    private final int COSINE_VALUES_SIZE = 8192;
    private final int COSINE_VALUES_SIZE_DOUBLE = COSINE_VALUES_SIZE * 2;
    private final double[] COSINE_VALUES = new double[COSINE_VALUES_SIZE];
    private final double PI_DOUBLE = Math.PI * 2;
    private final double PI_HALF = Math.PI / 2;

    public double sin(double angle) {
        return cos(angle - PI_HALF);
    }

    public double cos(double angle) {
        int index = (int) (angle / PI_DOUBLE % 1.0 * (double) COSINE_VALUES_SIZE_DOUBLE);
        if (index < 0) {
            index += COSINE_VALUES_SIZE_DOUBLE;
        }

        if (index >= COSINE_VALUES_SIZE) {
            return -COSINE_VALUES[index - COSINE_VALUES_SIZE];
        }

        return COSINE_VALUES[index];
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(Math.min(value, max), min);
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);
    }

    public static float lerp(float delta, float start, float end) {
        return start + delta * (end - start);
    }

    public static double lerp(double delta, double start, double end) {
        return start + delta * (end - start);
    }

    public static double round(double value, int places) {
        final BigDecimal bd = new BigDecimal(Double.toString(value));
        return bd.setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    static {
        for (int value = 0; value < COSINE_VALUES_SIZE; ++value) {
            COSINE_VALUES[value] = Math.cos(value / (float) COSINE_VALUES_SIZE * Math.PI);
        }
    }
}
