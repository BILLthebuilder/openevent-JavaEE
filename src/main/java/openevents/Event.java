package openevents;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String address;

    @Column
    private int organizerId;

    @Column(name = "attendee_id's")
    @ElementCollection
    private List<Integer> attendeeIds = new ArrayList<Integer>();

    @Column
    private boolean approved;


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

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public List<Integer> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(List<Integer> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
