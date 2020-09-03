package openevents.beans;
import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "organizers")
public class OrganizerBean extends BaseUserEntity {
    @Embedded
    Person organizer;

    @OneToMany(mappedBy = "organizer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EventBean> events = new ArrayList<>();

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }

    public List<EventBean> getEvents() {
        return events;
    }

    public void setEvents(List<EventBean> events) {
        this.events = events;
    }

    public void addEvent(EventBean event){
        event.setOrganizer(this);
        this.events.add(event);
    }
}
