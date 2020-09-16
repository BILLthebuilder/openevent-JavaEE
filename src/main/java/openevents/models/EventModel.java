package openevents.models;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

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
    private String eventTitle;

    //@NotNull
    @Column(name = "organizer")
    //@Formula("(SELECT id FROM organizers o where o.id=?)")
    private String organizerName;

    @NotNull
    @Column(name = "description")
    private String eventDescription;

    @Column(name = "tags")
    private String eventTags;

    @NotNull
    @Column(name = "location")
    private String eventLocation;


    @Column(name = "start_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date eventStartDateAndTime;


    @Column(name = "end_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date eventEndDateAndTime;

    @Column(name = "type")
    private String eventType;

    @Column(name ="category")
    private String eventCategory;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="organizer_id")
    @JsonBackReference
    private OrganizerModel organizer;

//    @Formula("organizer_id")
//    private int organizerId;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AttendeeModel> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<AttendeeModel> allAtendees = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String title) {
        this.eventTitle = title;
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

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
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

//    public int getOrganizerId() {
//        return organizerId;
//    }
//
//    public void setOrganizerId(int organizerId) {
//        this.organizerId = organizerId;
//    }

    public void addAttendee(AttendeeModel attendee){
        attendee.setEvent(this);
       this.attendees.add(attendee);
    }

}
