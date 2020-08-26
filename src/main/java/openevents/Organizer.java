package openevents;
import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "organizers")
public class Organizer extends BaseUserEntity {
    @Embedded
    Person organizer;

    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }
}
