package openevents.actions.events;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.*;

import database.HibernateHelper;
import openevents.Event;

@WebServlet(urlPatterns = { "/create-event" })
public class EventAction extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String address = request.getParameter("event_address");
        String approvedParam = request.getParameter("event_approval");
        Boolean approved = Boolean.valueOf(approvedParam);
        
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Event event = new Event();
            event.setAddress(address);
            event.setApproved(approved);
            session.save(event);
            response.getWriter().println("Data saved successfully!!");
            tx.commit();
        } catch (Exception e) {
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }

    }

    // public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //     Session session = HibernateHelper.getSessionFactory().getCurrentSession();
    //     Transaction tx = session.getTransaction();
    //     try {
    //         tx.begin();
    //         // List<TransactionClass>event = session.createQuery("From TransactionClass r").getResultList();
    //         // ObjectMapper json = new ObjectMapper();
    //         //response.getWriter().println(json.writeValueAsString(transactions));
    //         tx.commit();
    //     } catch (Exception e) {
    //         // TODO: handle exception properly
    //         response.getWriter().println("An error has occurred");
    //         e.printStackTrace();
    //     }
    // }

    // public void doDelete(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     final String id = request.getParameter("id");
    //     Session session = HibernateHelper.getSessionFactory().getCurrentSession();
    //     Transaction tx = session.getTransaction();
    //     try {
    //         tx.begin();
    //         // TransactionClass transcations = session.get(TransactionClass.class, Integer.parseInt(id));
    //         // session.delete(transcations);
    //         // response.getWriter().println("Data deleted Successfully!!");
    //         tx.commit();
    //     } catch (Exception e) {
    //         // TODO: handle exception properly
    //         response.getWriter().println("An error has occurred");
    //         e.printStackTrace();
    //     }
    // }
}
