package app.diploma.eventCollector.controller;

import app.diploma.eventCollector.model.Event;
import app.diploma.eventCollector.service.EventService;
import app.diploma.eventCollector.workWithEvents.ParsingTheReceivedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("collector/api")
public class EventCollectorController {

    @Autowired
    private EventService eventService;
    @Autowired
    private ParsingTheReceivedData parsingTheReceivedData;

    @GetMapping(value = "/getAllEvent")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/getEventRealTime")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Event> getEventRealTime() {
        return parsingTheReceivedData.getReceivedDataInWebUI();
    }


}
