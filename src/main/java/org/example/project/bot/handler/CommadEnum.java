package org.example.project.bot.handler;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommadEnum {
    START("/start"),
    HELP("/help");

    public final String value;

    private static Map<String, CommadEnum> map = Arrays.stream(CommadEnum.values())
            .collect(Collectors.toMap(c -> c.value, c -> c));


    CommadEnum(String value) {
        this.value = value;
    }

    public static CommadEnum of(String command) {
        CommadEnum commadEnum = map.get(command);
        if (commadEnum != null) {
            return commadEnum;
        }
        throw new IllegalArgumentException("Unsupported command.");
    }
}
