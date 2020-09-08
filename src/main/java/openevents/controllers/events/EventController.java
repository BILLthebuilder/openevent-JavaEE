package openevents.controllers.events;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.*;

import database.HibernateHelper;
import openevents.models.EventModel;

@WebServlet(urlPatterns = { "/event" })
public class EventController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String tittle = request.getParameter("event_title");
        String organizer = request.getParameter("event_organizer");
        String description = request.getParameter("event_description");
        String tags = request.getParameter("event_tags");
        String type = request.getParameter("event_type");
        String category = request.getParameter("event_category");
        String location = request.getParameter("event_location");
        String startDate = request.getParameter("event_start_date");
        String endDate = request.getParameter("event_end_date");

        
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            EventModel event = new EventModel();
            event.setLocation(location);
            event.setOrganizerName(organizer);
            event.seteventDescription(description);
            event.setEventType(type);
            //example date: 2012-01-31 23:59:59
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String startDateInString = startDate;
            String endDateInString = endDate;
            Date startDate_ = simpleDateFormat.parse(startDateInString);
            Date endDate_ = simpleDateFormat.parse(endDateInString);
            event.setEventStartDateAndTime(startDate_);
            event.setEventEndDateAndTime(endDate_);
            event.setEventCategory(category);
            event.setEventTags(tags);
            event.setTitle(tittle);
            session.save(event);
            response.getWriter().println("Data saved successfully!!");
            tx.commit();
        } catch (Exception e) {
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            List<EventModel> events = session.createQuery("From EventModel e").getResultList();
            ObjectMapper json = new ObjectMapper();
            String fetchedEvents = json.writerWithDefaultPrettyPrinter().writeValueAsString(events);
            response.getWriter().println(fetchedEvents);
            tx.commit();
        } catch (Exception e) {
            // TODO: handle exception properly
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }
    }

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
