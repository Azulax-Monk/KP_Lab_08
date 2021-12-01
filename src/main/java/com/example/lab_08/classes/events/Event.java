package com.example.lab_08.classes.events;

import com.example.lab_08.interfaces.IListener;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private List<IListener> listeners = new ArrayList<>();
    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public void addListener(IListener toAdd){
        listeners.add(toAdd);
    }

    public void invoke(){
        for(IListener l : listeners)
            l.notifyMe();
    }

    public EventType getType() {
        return type;
    }
}
