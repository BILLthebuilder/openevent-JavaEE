package openevents;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Organizer organizer;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendee> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "events",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Attendee> allAtendees = new HashSet<>();

    @Column
    private Boolean approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void addAttendee(Attendee attendee){
        attendee.setEvent(this);
       this.attendees.add(attendee);
    }

}
