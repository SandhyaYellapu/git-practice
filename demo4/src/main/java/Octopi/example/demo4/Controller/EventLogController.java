package Octopi.example.demo4.Controller;


import Octopi.example.demo4.Service.EventLogService;
import Octopi.example.demo4.Service.JsonConverter;
import Octopi.example.demo4.User.EventLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.awt.desktop.ScreenSleepEvent;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventLogController
{
@Autowired
    private EventLogService eventLogService;
    @PostMapping
    public ResponseEntity<EventLog> saveEvent(
            @RequestParam String eventName,
            @RequestBody Map<String,Object> payload)
    {
         EventLog log=eventLogService.saveEvent(eventName,payload);
          return ResponseEntity.ok(log);
    }

}
