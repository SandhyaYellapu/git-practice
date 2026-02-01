package Octopi.example.demo4.User;

import Octopi.example.demo4.Service.JsonConverter;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import org.springframework.context.annotation.Configuration;

@Entity
@Table(name = "event_logs")
public class EventLog
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonConverter.class)
    private Object payload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
