package ru.job4j.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Getter
public class Permission {

    private int id;

    private String name;

    @Singular("accessBy")
    private List<String> rules;

    public static void main(String[] args) {
        Permission permission = Permission.builder()
                .id(1)
                .name("name")
                .accessBy("any string1")
                .accessBy("any string2")
                .accessBy("any string3")
                .build();
    }
}