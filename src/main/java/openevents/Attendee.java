package openevents;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "attendees")
public class Attendee extends BaseUserEntity {

    @Embedded
    Person attendee;

    @Column(name = "rsvp",nullable = false)
    private Boolean RSVP;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

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

}
