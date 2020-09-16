package openevents.ejb.events;

import openevents.models.EventModel;
import openevents.models.OrganizerModel;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class EventBean {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private EventModel eventModel;

    final static private String INVALID_ID_ERROR = "Invalid Event Id";
    final static private String EVENT_NOT_FOUND = "No events found";

    public EventModel createEvent(EventModel event, int organizerId) throws Exception {
        if (event == null) {
            throw new Exception("Invalid event details");
        }
        //eventModel.getOrganizerId(organizerId);
        OrganizerModel organizer = this.em.getReference(OrganizerModel.class, organizerId);
        event.setOrganizer(organizer);

        return em.merge(event);
    }

    @SuppressWarnings({ "unchecked" })
    public List<EventModel> getAllEvents() throws Exception {
        List<EventModel> events = em.createQuery("SELECT e FROM EventModel e").getResultList();
        if (events.isEmpty()) {
            throw new Exception(EVENT_NOT_FOUND);
        }
        return events;
    }

    public EventModel editEvent(EventModel eventModel, int eventId) throws Exception {
        if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);

        this.eventModel = this.findOneEvent(eventId);
        if (eventModel == null)
            throw new Exception(EVENT_NOT_FOUND);

       return em.merge(eventModel);
    }

    public EventModel findOneEvent(int eventId) throws Exception{
          if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);

      this.eventModel = em.find(EventModel.class, eventId);

        if (eventModel == null)
            throw new Exception(EVENT_NOT_FOUND);

        return eventModel;
    }

    public int deleteEvent(int eventId) throws Exception {
        if (eventId == 0)
            throw new Exception(INVALID_ID_ERROR);

        eventModel = this.findOneEvent(eventId);
         
        em.remove(eventModel);
        return eventModel.getId();
    }

}
