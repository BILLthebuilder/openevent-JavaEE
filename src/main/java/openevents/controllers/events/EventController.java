package openevents.controllers.events;

import java.io.IOException;

import openevents.ejb.events.EventBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.PrintWriter;
import java.util.List;


import openevents.models.EventModel;

@WebServlet(urlPatterns = { "/event" })
public class EventController extends HttpServlet {

    @EJB
    private EventBean eventBean;

    @Inject
    EventModel event;

    PrintWriter writer;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writer = response.getWriter();

        try {
            BeanUtils.populate(event, request.getParameterMap());
            String id = request.getParameter("id");
            eventBean.createEvent(event,Integer.parseInt(id));
            writer.println("Event Created Successfully");
        } catch (Exception e) {
            writer.println("An error has occurred");
            e.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writer = response.getWriter();
        try {
            List<EventModel> events = eventBean.getAllEvents();
            ObjectMapper json = new ObjectMapper();
            String fetchedEvents = json.writerWithDefaultPrettyPrinter().writeValueAsString(events);
            writer.println(fetchedEvents);
        } catch (Exception e) {
            writer.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
