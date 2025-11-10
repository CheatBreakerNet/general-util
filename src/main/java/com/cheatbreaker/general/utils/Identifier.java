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

import lombok.Getter;
import org.jspecify.annotations.Nullable;

import java.util.regex.Pattern;

@Getter
public class Identifier {
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("[a-z0-9_.-]+");
    private static final Pattern PATH_PATTERN = Pattern.compile("[A-Za-z0-9/._-]+");

    private final String namespace;
    private final String path;

    private Identifier(String namespace, String path) {
        assert isValidNamespace(namespace);
        this.namespace = namespace;

        assert isValidPath(path);
        this.path = path;
    }

    public static Identifier of(String input) throws InvalidException {
        if (input == null) {
            throw new InvalidException("Invalid identifier input, input is null!");
        } else {
            String namespace = "minecraft";
            String path;

            String[] parts = input.split(":", 2);
            if (parts.length == 2) {
                namespace = parts[0];
                path = parts[1];
            } else {
                path = parts[0];
            }

            if (!isValidNamespace(namespace)) {
                throw new InvalidException("Invalid identifier namespace \"" + input + "\", only accepts " + NAMESPACE_PATTERN.pattern());
            }

            if (!isValidPath(path)) {
                throw new InvalidException("Invalid identifier path \"" + input + "\", only accepts " + PATH_PATTERN.pattern());
            }

            return new Identifier(namespace, path);
        }
    }

    public static Identifier of(String namespace, String path) {
        return new Identifier(namespace, path);
    }

    public static @Nullable Identifier ofNullable(String input) {
        try {
            return of(input);
        } catch (InvalidException exception) {
            System.out.println("Failed to parse identifier! " + exception);
            exception.printStackTrace();
            return null;
        }
    }

    public Identifier withNamespace(String namespace) throws InvalidException {
        if (!isValidNamespace(namespace)) {
            throw new InvalidException("Invalid identifier namespace \"" + namespace + "\", only accepts " + NAMESPACE_PATTERN.pattern());
        } else {
            return Identifier.of(namespace, this.path);
        }
    }

    public Identifier withPath(String path) throws InvalidException {
        if (!isValidPath(path)) {
            throw new InvalidException("Invalid identifier path \"" + path + "\", only accepts " + PATH_PATTERN.pattern());
        } else {
            return Identifier.of(this.namespace, path);
        }
    }

    public static boolean isValidNamespace(String namespace) {
        return namespace != null && NAMESPACE_PATTERN.matcher(namespace).matches();
    }

    public static boolean isValidPath(String path) {
        return path != null && PATH_PATTERN.matcher(path).matches();
    }

    @Override
    public String toString() {
        return this.namespace + ":" + this.path;
    }

    public static class InvalidException extends RuntimeException {
        public InvalidException(String message) {
            super(message);
        }
    }
}
