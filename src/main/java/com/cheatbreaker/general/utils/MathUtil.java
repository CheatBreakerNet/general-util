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

public final class MathUtil {
    private static final int COSINE_VALUES_SIZE = 8192;
    private static final int COSINE_VALUES_SIZE_DOUBLE = COSINE_VALUES_SIZE * 2;
    private static final double[] COSINE_VALUES = new double[COSINE_VALUES_SIZE];
    private static final double PI_DOUBLE = Math.PI * 2;
    private static final double PI_HALF = Math.PI / 2;

    private MathUtil() {
    }

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

    static {
        for (int value = 0; value < COSINE_VALUES_SIZE; ++value) {
            COSINE_VALUES[value] = Math.cos(value / (float) COSINE_VALUES_SIZE * Math.PI);
        }
    }
}
