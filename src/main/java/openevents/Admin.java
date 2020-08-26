package openevents;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "admin")
public class Admin extends BaseUserEntity {

    @Embedded
    Person admin;

    public Person getAdmin() {
        return admin;
    }

    public void setAdmin(Person admin) {
        this.admin = admin;
    }

}
