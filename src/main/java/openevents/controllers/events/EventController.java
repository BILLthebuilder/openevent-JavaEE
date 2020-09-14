package openevents.controllers.events;

import java.io.IOException;

import openevents.ejb.events.EventBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


import openevents.models.EventModel;

@WebServlet(urlPatterns = { "/event" })
public class EventController extends HttpServlet {

    @EJB
    private EventBean eventBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            EventModel event = new EventModel();
            BeanUtils.populate(event, request.getParameterMap());
            eventBean.createEvent(event);
        } catch (Exception e) {
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<EventModel> events = eventBean.getAllEvents();
            ObjectMapper json = new ObjectMapper();
            String fetchedEvents = json.writerWithDefaultPrettyPrinter().writeValueAsString(events);
            response.getWriter().println(fetchedEvents);
        } catch (Exception e) {
            // TODO: handle exception properly
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }
    }

}
