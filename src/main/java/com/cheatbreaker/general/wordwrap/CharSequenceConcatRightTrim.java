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

public final class CharSequenceConcatRightTrim implements CharSequence {
    private final CharSequence charSequenceA;
    private final CharSequence charSequenceB;

    CharSequenceConcatRightTrim(CharSequence charSequenceA, CharSequence charSequenceB) {
        this.charSequenceA = charSequenceA;
        this.charSequenceB = charSequenceB;
    }

    @Override
    public int length() {
        int i = charSequenceA.length() + charSequenceB.length() - 1;
        while (i > 0 && Character.isWhitespace(charAt(i))) {
            i--;
        }
        return i + 1;
    }

    @Override
    public char charAt(int index) {
        if (index < charSequenceA.length()) {
            return charSequenceA.charAt(index);
        } else {
            return charSequenceB.charAt(index - charSequenceA.length());
        }
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new CharSequence() {

            @Override
            public int length() {
                return end - start;
            }

            @Override
            public char charAt(int index) {
                return CharSequenceConcatRightTrim.this.charAt(start + index);
            }

            @Override
            public CharSequence subSequence(int start2, int end2) {
                // only support one level of substring
                StringBuilder s = new StringBuilder(end2 - start2);
                for (int i = start2; i < end2; i++) {
                    s.append(charAt(i));
                }
                return s;
            }

            @Override
            public String toString() {
                StringBuilder s = new StringBuilder();
                int len = length();
                for (int i = 0; i < len; i++) {
                    s.append(charAt(i));
                }
                return s.toString();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            builder.append(charAt(i));
        }
        return builder.toString();
    }
}
