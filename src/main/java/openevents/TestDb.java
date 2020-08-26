package openevents;

import database.HibernateHelper;
import openevents.utils.Person;
import org.hibernate.*;

public class TestDb {
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
      adminTests();

    }
}
