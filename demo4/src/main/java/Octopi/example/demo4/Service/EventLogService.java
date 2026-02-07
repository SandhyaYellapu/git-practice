package Octopi.example.demo4.Service;

import Octopi.example.demo4.Repository.EventRepository;
import Octopi.example.demo4.User.EventLog;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class EventLogService
{

    private final EventRepository eventRepository;

    public EventLogService(EventRepository repository)
    {
        this.eventRepository=repository;
    }

    public EventLog saveEvent(String eventName,Object requestData)
    {
        try {
//            String jsonPayload=objectMapper.writeValueAsString(requestData);
            EventLog log=new EventLog();
            log.setName(eventName);
            log.setPayload(requestData);
            return eventRepository.save(log);
            //event service
            //this is service class for events
        }
        catch (JsonParseException  e)
        {
            throw new RuntimeException("Failed to convert json to object");
        }
    }
}
