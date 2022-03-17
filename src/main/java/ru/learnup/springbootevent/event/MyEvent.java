package ru.learnup.springbootevent.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class MyEvent extends ApplicationEvent {

    private final String event;

    public MyEvent(String event) {
        super(event);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
