package com.example.lab_08.classes.events;

import java.util.ArrayList;
import java.util.List;

public class EventPool {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);
    }
    
    public Event getEvent(EventType type){
        return events.stream().filter(e->e.getType().equals(type)).findFirst().orElse(null);
    }
}
