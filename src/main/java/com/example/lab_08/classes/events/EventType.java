package com.example.lab_08.classes.events;

public class EventType {
    private String name;

    public static final EventType ENTITY_STATE_CHANGED = new EventType("ENTITY_STATE_CHANGED");
    public static final EventType ENTITY_COUNT_CHANGED = new EventType("ENTITY_COUNT_CHANGED");
    public static final EventType ITEM_CREATED = new EventType("ITEM_CREATED");
    public static final EventType ITEM_STORED = new EventType("ITEM_STORED");

    public EventType(String name) {
        this.name = name;
    }
}