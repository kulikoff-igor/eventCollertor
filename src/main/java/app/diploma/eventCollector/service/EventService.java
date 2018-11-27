package app.diploma.eventCollector.service;

import app.diploma.eventCollector.model.Event;
import app.diploma.eventCollector.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("eventService")
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Boolean findEvent(String fingerPrint) {
        return eventRepository.existsByFingerPrint(fingerPrint);
    }
}
