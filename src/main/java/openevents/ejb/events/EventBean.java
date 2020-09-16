package openevents.ejb.events;

import openevents.models.EventModel;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class EventBean {
    @PersistenceContext
    private EntityManager em;

    private EventModel eventModel;

    final static private String INVALID_ID_ERROR = "Invalid Event Id";

    public EventModel createEvent(EventModel event) throws Exception {
        if (event == null) {
            throw new Exception("Invalid event details");
        }

        return em.merge(event);
    }

    @SuppressWarnings({ "unchecked" })
    public List<EventModel> getAllEvents() throws Exception {
        List<EventModel> events = em.createQuery("SELECT e FROM EventModel e").getResultList();
        if (events.isEmpty()) {
            throw new Exception("No events found");
        }
        return events;
    }

    public EventModel editEvent(int eventId) throws Exception {
        if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);


        this.eventModel = this.findOneEvent(eventId);
        this.createEvent(eventModel);

        if (eventModel == null)
            throw new Exception("No event found");

       return eventModel;
    }

    public EventModel findOneEvent(int eventId) throws Exception{
          if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);

      this.eventModel = em.find(EventModel.class, eventId);

        if (eventModel == null)
            throw new Exception("Event not found");

        return eventModel;
    }

    public int deleteEvent(int eventId) throws Exception {
        if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);

         eventModel = em.find(EventModel.class, eventId);

        em.remove(eventModel);
        return eventModel.getId();
    }
    

}
