package openevents;

import database.HibernateHelper;
import openevents.utils.Person;
import org.hibernate.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDb {
    public static void main(String args[]) throws ParseException {
        //adminTests();
        //createOrganizer();
        //savingAttendeeUsingEvent();
        savingEventsUsingOrganizer();


    }
    public static void savingEventsUsingOrganizer() throws ParseException {
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
       Organizer organizer = new Organizer();
        organizer.setOrganizer(new Person());
        organizer.getOrganizer().setName("Bill");
        organizer.getOrganizer().setEmail("bill@email.com");
        organizer.getOrganizer().setPhoneNumber("0700000009");


        for (int idx = 0; idx<10; idx++) {
            Event event = new Event();
            event.setLocation("Nairobi");
            event.setOrganizerName("Bill");
            event.setEventType("Tech Event");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateInString = "2020-09-07 10:20:00";
            Date date = simpleDateFormat.parse(dateInString);
            event.setEventStartDateAndTime(date);
            event.setEventEndDateAndTime(date);
            event.setEventCategory("something");
            event.setEventTags("tech-sports-enjoy");
            event.setTitle("Biggest Tech Event");

            organizer.addEvent(event);
        }

        session.save(organizer);

        tx.commit();
    }
    public static void savingAttendeeUsingEvent(){
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
        Event event = new Event();
        event.setLocation("Nairobi");


        for (int idx = 0; idx<10; idx++) {
            Attendee attendee = new Attendee();
            attendee.setAttendee(new Person());

            attendee.setEvent(event);
            attendee.getAttendee().setName("Bill");
            attendee.getAttendee().setEmail("bill@email.com");
            attendee.getAttendee().setPhoneNumber("0700000009");
            attendee.setRSVP(true);
            event.addAttendee(attendee);
        }

        session.save(event);

        tx.commit();

    }
   public static void adminTests(){
       Session session = HibernateHelper.getSessionFactory().getCurrentSession();

       Transaction tx = session.getTransaction();
       tx.begin();
       Admin admin = new Admin();
       admin.setAdmin(new Person());

       admin.getAdmin().setName("admin");
       admin.getAdmin().setEmail("admin@mail.com");

       session.save(admin);

       tx.commit();
   }

}
