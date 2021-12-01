package com.example.lab_08.interfaces;

import com.example.lab_08.classes.events.EventPool;
import com.example.lab_08.classes.events.EventType;

public interface INotifier {
    void setEventPool(EventPool ep);

    EventPool getEventPool();

    default void notify(EventPool ep, EventType type) {
        if (ep != null)
            ep.getEvent(type).invoke();
    }
}
