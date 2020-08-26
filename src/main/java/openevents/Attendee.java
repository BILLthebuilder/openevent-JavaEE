package openevents;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;

import javax.persistence.*;

@Entity
@Table(name = "attendees")
public class Attendee extends BaseUserEntity {

    @Embedded
    Person attendee;

    @Column(name = "rsvp",nullable = false)
    private boolean RSVP;

    public Person getAttendee() {
        return attendee;
    }

    public void setAttendee(Person attendee) {
        this.attendee = attendee;
    }

    public boolean isRSVP() {
        return RSVP;
    }

    public void setRSVP(boolean RSVP) {
        this.RSVP = RSVP;
    }

}
