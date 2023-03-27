package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

    MESSAGE("Сообщение"),
    PARKING("Парковка"),
    CHECKPOINTS("Контрольная точка"),
    ANXIETY("Тревога");

    private final String rusName;
}
