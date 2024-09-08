package com.github.nutt1101.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Bed {
    String id;
    Student student;
}
