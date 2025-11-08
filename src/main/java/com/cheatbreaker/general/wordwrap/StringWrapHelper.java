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

package com.cheatbreaker.general.wordwrap;

import java.util.Arrays;

public final class StringWrapHelper implements CharSequence {
    private char[] chars;
    private int length;

    StringWrapHelper(String s) {
        this(s.toCharArray(), s.length());
    }

    StringWrapHelper() {
        this(new char[16], 0);
    }

    private StringWrapHelper(char[] chars, int length) {
        this.chars = chars;
        this.length = length;
    }

    char[] internalArray() {
        return chars;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        char[] chars2 = new char[end - start];
        System.arraycopy(chars, start, chars2, 0, end - start);
        return new StringWrapHelper(chars2, chars2.length);
    }

    public void append(StringWrapHelper s) {
        int len = s.length();
        checkSize(len);
        System.arraycopy(s.chars, 0, chars, length, len);
        length += len;
    }

    private void checkSize(int len) {
        if (length + len > chars.length) {
            chars = Arrays.copyOf(chars, newSize(len));
        }
    }

    private int newSize(int len) {
        int newSize = chars.length * 2;
        if (newSize < length + len) {
            newSize = length + len;
        }
        return newSize;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void append(char ch) {
        checkSize(1);
        chars[length] = ch;
        length++;
    }

    public void delete(int start, int end) {
        System.arraycopy(chars, end, chars, start, length - end);
        length -= end - start;
    }

    public String substring(int start, int end) {
        return new String(chars, start, end - start);
    }

    @Override
    public String toString() {
        return new String(chars, 0, length);
    }

    /**
     * Trims right space from this and returns {@code this}.
     *
     * @return this
     */
    StringWrapHelper rightTrim() {
        int i = length();
        while (i > 0) {
            if (!Character.isWhitespace(charAt(i - 1))) {
                break;
            }
            i--;
        }
        length = i;
        return this;
    }
}
