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

    public EventModel createEvent(EventModel event) throws Exception {
        if (event == null) {
            throw new Exception("Invalid event details");
        }

        return em.merge(event);
    }

    @SuppressWarnings({"unchecked"})
    public List<EventModel> getAllEvents() throws Exception {
        List<EventModel> events = em.createQuery("SELECT e FROM EventModel e").getResultList();
        if (events.isEmpty()) {
            throw new Exception("No events found");
        }
        return events;
    }

}
