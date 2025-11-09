package com.cheatbreaker.general.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;

@AllArgsConstructor
@Getter
public class Identifier {
    private static final Pattern PATTERN = Pattern.compile("");

    private final String namespace;
    private final String path;

    public static Identifier parse(String input) throws InvalidException {
        if (input == null) {
            throw new InvalidException("Invalid identifier input, input is null!");
        }

        if (!PATTERN.matcher(input).matches()) {
            throw new InvalidException("Invalid identifier input, identifier only accepts " + PATTERN.pattern());
        }

        String namespace = "minecraft";
        String path;

        String[] parts = input.split(":");
        if (parts.length == 0) {
            path = input;
        } else {
            namespace = parts[0];
            path = input.substring(namespace.length());
        }

        return new Identifier(namespace, path);
    }

    public static class InvalidException extends Exception {
        public InvalidException(String message) {
            super(message);
        }
    }
}
