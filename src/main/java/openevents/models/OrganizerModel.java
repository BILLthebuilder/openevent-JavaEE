package openevents.models;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "organizers")
public class OrganizerModel extends BaseUserEntity {
    @Embedded
    Person organizer;

    @OneToMany(mappedBy = "organizer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EventModel> events = new ArrayList<>();

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    public void addEvent(EventModel event){
        event.setOrganizer(this);
        this.events.add(event);
    }
}
