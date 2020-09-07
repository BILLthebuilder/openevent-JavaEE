package openevents.models;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "attendees")
public class AttendeeModel extends BaseUserEntity {
    @Embedded
    Person attendee;

    @Column(name = "rsvp",nullable = false)
    private Boolean RSVP;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EventModel event;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_attendance",
            joinColumns = { @JoinColumn(name = "attendee_id") },
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private Set<EventModel> events = new HashSet<>();

    public Person getAttendee() {
        return attendee;
    }

    public void setAttendee(Person attendee) {
        this.attendee = attendee;
    }

    public boolean isRSVP() {
        return RSVP;
    }

    public void setRSVP(Boolean RSVP) {
        this.RSVP = RSVP;
    }
    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

}
