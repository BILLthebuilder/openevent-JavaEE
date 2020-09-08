package openevents.models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "events")
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column(name = "organizer")
    private String organizerName;

    @NotNull
    @Column(name = "description")
    private String eventDescription;

    @Column(name = "tags")
    private String eventTags;

    @NotNull
    @Column
    private String location;

    @NotNull
    @Column(name = "start_date", columnDefinition = "DATETIME")
    private Date eventStartDateAndTime;

    @NotNull
    @Column(name = "end_date", columnDefinition = "DATETIME")
    private Date eventEndDateAndTime;

    @Column(name = "type")
    private String eventType;

    @Column(name ="category")
    private String eventCategory;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private OrganizerModel organizer;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AttendeeModel> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<AttendeeModel> allAtendees = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String geteventDescription() {
        return eventDescription;
    }

    public void seteventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getEventTags() {
        return eventTags;
    }

    public void setEventTags(String eventTags) {
        this.eventTags = eventTags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getEventStartDateAndTime() {
        return eventStartDateAndTime;
    }

    public Date getEventEndDateAndTime() {
        return eventEndDateAndTime;
    }

    public void setEventEndDateAndTime(Date eventEndDateAndTime) {
        this.eventEndDateAndTime = eventEndDateAndTime;
    }

    public void setEventStartDateAndTime(Date eventStartDateAndTime) {
        this.eventStartDateAndTime = eventStartDateAndTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public OrganizerModel getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizerModel organizer) {
        this.organizer = organizer;
    }

    public List<AttendeeModel> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<AttendeeModel> attendees) {
        this.attendees = attendees;
    }

    public Set<AttendeeModel> getAllAtendees() {
        return allAtendees;
    }

    public void setAllAtendees(Set<AttendeeModel> allAtendees) {
        this.allAtendees = allAtendees;
    }

    public void addAttendee(AttendeeModel attendee){
        attendee.setEvent(this);
       this.attendees.add(attendee);
    }

}
