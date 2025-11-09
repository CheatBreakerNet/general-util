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

    public static class InvalidException extends Exception {
        public InvalidException(String message) {
            super(message);
        }
    }
}
