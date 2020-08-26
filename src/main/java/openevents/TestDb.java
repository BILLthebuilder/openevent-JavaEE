package openevents;

import database.HibernateHelper;
import openevents.utils.Person;
import org.hibernate.*;

public class TestDb {
    public static void createAttendees(){
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
       Attendee attendee = new Attendee();
       attendee.setAttendee(new Person());

       attendee.getAttendee().setName("Bill");
       attendee.getAttendee().setEmail("bill@email.com");
       attendee.getAttendee().setPhoneNumber(734049589);
       attendee.setRSVP(true);

       session.save(attendee);

        tx.commit();
    }
    public static void createOrganizer(){
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
        Organizer organizer = new Organizer();
        organizer.setOrganizer(new Person());

        organizer.getOrganizer().setName("Bill");
        organizer.getOrganizer().setEmail("bill@email.com");
        organizer.getOrganizer().setPhoneNumber(734049589);
        session.save(organizer);

        tx.commit();

    }
    public static void eventTest(){
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();

        Transaction tx = session.getTransaction();
        tx.begin();
        Event event = new Event();
        Organizer organizer = new Organizer();
        //int organizerId = (Integer)organizer.getId();
        event.setAddress("Nairobi");
        event.setApproved(true);
        event.setOrganizer(organizer);

        session.save(event);

        tx.commit();

    }
   public static void adminTests(){
       Session session = HibernateHelper.getSessionFactory().getCurrentSession();

       Transaction tx = session.getTransaction();
       tx.begin();
       Admin admin = new Admin();
       admin.setAdmin(new Person());

       admin.getAdmin().setName("Person");
       admin.getAdmin().setEmail("admin@mail.com");

       session.save(admin);

       tx.commit();
   }
    public static void main(String args[]){
      //adminTests();
        //createOrganizer();
        //eventTest();
        createAttendees();


    }
}
