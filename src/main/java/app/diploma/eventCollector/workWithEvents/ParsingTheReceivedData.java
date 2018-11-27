package app.diploma.eventCollector.workWithEvents;

import app.diploma.eventCollector.configuration.HttpUrlConfig;
import app.diploma.eventCollector.model.Annotations;
import app.diploma.eventCollector.model.Event;
import app.diploma.eventCollector.model.Label;
import app.diploma.eventCollector.service.AnnotationsService;
import app.diploma.eventCollector.service.EventService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Component("parsingTheReceivedData")
public class ParsingTheReceivedData {
    private static final Logger log = LoggerFactory.getLogger(ParsingTheReceivedData.class);
    @Autowired
    private AnnotationsService annotationsService;

    @Autowired
    private EventService eventService;

    @Autowired
    private HttpUrlConfig configuration;

    private Annotations annotations;
    private Label label;
    private Event event;


    @Scheduled(fixedRate = 300000)
    public void addReceivedDataInDatabase() {
        Object obj = null;
        obj = getObject(obj);
        if (obj == null) {
            log.error("Data null");

        } else {

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");

            for (Object objectArray : jsonArray) {

                JSONObject jsonObjectArray = (JSONObject) objectArray;
                if (eventService.findEvent((String) jsonObjectArray.get("fingerprint")) != true) {
                    parsingTheReceivedData(jsonObjectArray);
                    annotationsService.addAnnotations(annotations);
                    eventService.addEvent(event);
                    annotations = null;
                    label = null;
                    event = null;
                    log.info("ADD Event", event);
                } else {
                    log.info("EventIsExist");
                }
            }

        }
    }

    private void parsingTheReceivedData(JSONObject jsonObjectArray) {
        JSONObject jsonObjectAnnotation = (JSONObject) jsonObjectArray.get("annotations");
        JSONObject jsonObjectStatus = (JSONObject) jsonObjectArray.get("status");

        event = new Event();
        event.setStartsAt((String) jsonObjectArray.get("startsAt"));
        event.setEndsAt((String) jsonObjectArray.get("endsAt"));
        event.setGeneratorURL((String) jsonObjectArray.get("generatorURL"));
        event.setState((String) jsonObjectStatus.get("state"));
        event.setFingerPrint((String) jsonObjectArray.get("fingerprint"));
        annotations = new Annotations();
        annotations.setDescription((String) jsonObjectAnnotation.get("description"));
        annotations.setSummary((String) jsonObjectAnnotation.get("summary"));
        event.setAnnotations(annotations);
        List<Label> labelList = new ArrayList<>();
        Map array = ((Map) jsonObjectArray.get("labels"));
        Iterator<Map.Entry> itr1 = array.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            label = new Label();
            label.setEvent(event);
            label.setLabelName((String) pair.getKey());
            label.setLabelData((String) pair.getValue());
            labelList.add(label);
        }

        event.setLabelList(labelList);
    }

    public List<Event> getReceivedDataInWebUI() {
        List<Event> eventList = null;
        Object obj = null;
        obj = getObject(obj);
        if (obj == null) {
            log.error("Data null");
        } else {

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            eventList = new ArrayList<>();
            for (Object objectArray : jsonArray) {
                annotations = null;
                label = null;
                event = null;
                JSONObject jsonObjectArray = (JSONObject) objectArray;
                parsingTheReceivedData(jsonObjectArray);
                eventList.add(event);
            }
        }
        return eventList;
    }

    private String sendGet() {
        String url = configuration.getPath();
        URL obj;
        int responseCode;
        StringBuffer response = null;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (ProtocolException e) {
            log.error("error Host", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();

    }

    private Object getObject(Object obj) {
        try {
            if (obj == null) {
                log.error("Data null");

            } else {
                obj = new JSONParser().parse(sendGet());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
