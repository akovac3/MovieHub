package com.moviehub.systemevents.model;

import com.moviehub.systemevents.model.enums.ActionType;
import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime date;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    @Size(max = 255)
    private String microservice;

    private String person;

    @Column(nullable = false)
    private ActionType action;

    @Column(nullable = false)
    private String resource;

    @Column(nullable = false)
    private int status;

    public Event() {
    }

    public Event(Long id, LocalDateTime date, @NotBlank @Size(max = 255) String microservice, String person, ActionType action, String resource, int status) {
        this.id = id;
        this.date = date;
        this.microservice = microservice;
        this.person = person;
        this.action = action;
        this.resource = resource;
        this.status = status;
    }

    public Event(@NotBlank @Size(max = 255) String microservice, String person, ActionType action, String resource, int status) {
        this.microservice = microservice;
        this.person = person;
        this.action = action;
        this.resource = resource;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMicroservice() {
        return microservice;
    }

    public void setMicroservice(String microservice) {
        this.microservice = microservice;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", microservice='" + microservice + '\'' +
                ", person='" + person + '\'' +
                ", action=" + action +
                ", resource='" + resource + '\'' +
                ", status=" + status +
                '}';
    }
}
