package ru.practicum.explorewithme.statsclient;

import org.springframework.stereotype.Component;
import ru.practicum.explorewithme.model.event.Event;

import java.util.ArrayList;
import java.util.List;

@Component
public class UriListMaker {

    public List<String> make(List<Event> eventList) {
        List<String> uriList = new ArrayList<>();
        eventList.forEach(event -> uriList.add("/events/" + event.getId()));
        return uriList;
    }
}
