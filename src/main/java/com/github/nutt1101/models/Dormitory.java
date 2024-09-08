package com.github.nutt1101.models;

import lombok.Getter;

@Getter
public enum Dormitory {
    DA_YEH(1),
    Four_Willing(2),
    Diligent(5),
    Lok_Chun(6);

    private final int id;

    Dormitory(int id) {
        this.id = id;
    }
}
