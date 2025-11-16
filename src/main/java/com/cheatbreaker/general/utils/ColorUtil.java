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

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public final class ColorUtil {
    private static final Pattern CBformattingCodePattern = Pattern.compile("(?i)" + '§' + "[0-9A-FR]");

    public static String formatColor(String input) {
        Matcher matcher = CBformattingCodePattern.matcher(input);
        String group = "";
        while (matcher.find()) {
            group = matcher.group();
        }

        return group;
    }

    @UtilityClass
    public static final class ARGB {
        public static float red(int value) {
            return (float) redBits(value) / 255.0F;
        }

        public static int redBits(int value) {
            return value >> 16 & 255;
        }

        public static float green(int value) {
            return (float) greenBits(value) / 255.0F;
        }

        public static int greenBits(int value) {
            return value >> 8 & 255;
        }

        public static float blue(int value) {
            return (float) blueBits(value) / 255.0F;
        }

        public static int blueBits(int value) {
            return value & 255;
        }

        public static float alpha(int value) {
            return (float) alphaBits(value) / 255.0F;
        }

        public static int alphaBits(int value) {
            return value >> 24 & 255;
        }

        public static int color(int red, int green, int blue, int alpha) {
            return (alpha & 255) << 24 | (red & 255) << 16 | (green & 255) << 8 | blue & 255;
        }

        public static int colorFloat(float red, float green, float blue, float alpha) {
            return color((int) ((double) (red * 255.0F) + 0.5), (int) ((double) (green * 255.0F) + 0.5), (int) ((double) (blue * 255.0F) + 0.5), (int) ((double) (alpha * 255.0F) + 0.5));
        }

        public static Color getAwtColor(int argb) {
            return new Color(red(argb), green(argb), blue(argb), alpha(argb));
        }
    }

    @UtilityClass
    public static final class RGBA {
        public static int color(int red, int green, int blue, int alpha) {
            return (red & 255) << 24 | (green & 255) << 16 | (blue & 255) << 8 | alpha & 255;
        }

        public static int colorFloat(float red, float green, float blue, float alpha) {
            return color((int) ((double) (red * 255.0F) + 0.5), (int) ((double) (green * 255.0F) + 0.5), (int) ((double) (blue * 255.0F) + 0.5), (int) ((double) (alpha * 255.0F) + 0.5));
        }

        public static Color getAwtColor(int rgba) {
            return new Color(rgba, true);
        }
    }
}
