package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (!values.keySet().contains(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] keyValue;
        for (String arg : args) {
            String newArg = arg.replace("-", "");
            keyValue = newArg.split("=", 2);
            values.put(keyValue[0], keyValue[1]);
        }

    }

    private static void validation(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }
            if (arg.indexOf('=') == arg.lastIndexOf('=') && arg.endsWith("=")
            ) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
            }
            if (arg.charAt(0) == '-' && arg.charAt(1) == '=') {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
            }
        }
    }

    public static ArgsName of(String[] args) throws IllegalArgumentException {
        validation(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
        System.out.println(zip.get("encoding"));
    }
}