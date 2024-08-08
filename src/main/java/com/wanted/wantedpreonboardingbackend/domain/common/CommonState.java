package com.wanted.wantedpreonboardingbackend.domain.common;

import java.util.Arrays;

public enum CommonState {
    DELETED(0),
    ACTIVE(1);

    private final int value;

    public int getValue() {
        return value;
    }

    CommonState(int value) {
        this.value = value;
    }

    public static CommonState parse(int value) {
//        return Arrays.stream(values())
//                .filter(s -> s.value == value)
//                .findFirst()
//                .orElse(CommonState.ACTIVE);
        for (CommonState state : values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        return CommonState.ACTIVE;
    }
}
