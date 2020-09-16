package openevents.controllers.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import openevents.ejb.events.EventBean;
import openevents.models.EventModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/fetchevent"})
public class editEvent extends HttpServlet {
    @EJB
    private EventBean eventBean;

    @Inject
    EventModel event;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            BeanUtils.populate(event, request.getParameterMap());
            EventModel data = eventBean.editEvent(event,Integer.parseInt(id));
            ObjectMapper json = new ObjectMapper();
            String fetchedEvent = json.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            response.getWriter().println(fetchedEvent);
        }catch (Exception e){
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        }
    }

        public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                String id = request.getParameter("id");
                System.out.println(id);
                eventBean.deleteEvent(Integer.parseInt(id));
                response.getWriter().println(id);
            }catch (Exception e){
                response.getWriter().println(e.getMessage());
                e.printStackTrace();
            }
        }
}