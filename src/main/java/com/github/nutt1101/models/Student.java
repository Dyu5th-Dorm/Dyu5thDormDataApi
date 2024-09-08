package com.github.nutt1101.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Student {
    String id;
    String name;
    String sex;
    String citizenship;
    String major;
}
