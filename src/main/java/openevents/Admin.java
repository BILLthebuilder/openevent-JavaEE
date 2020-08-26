package openevents;

import openevents.utils.BaseUserEntity;
import openevents.utils.Person;

import javax.persistence.*;


@Entity
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
