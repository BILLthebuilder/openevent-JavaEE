package openevents.models;

import openevents.utils.BaseUserEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users")//uniqueConstraints = @UniqueConstraint(columnNames = {"email","first_name"}))
// @NamedQueries({
//         @NamedQuery(name = UserModel.USER_FIND_BY_EMAIL_PWD, query = "SELECT u FROM UserModel u WHERE u.email=:email")
// })
public class UserModel extends BaseUserEntity{

    // @Transient
    // public static final String USER_FIND_BY_EMAIL_PWD = "UserModel.findByEmailPwd";

@NotNull
@Column(name = "first_name")
private String firstName;

@NotNull
@Column(name = "last_name")
private String lastName;

@NotNull
@Column()
private String email;

@NotNull
@Column(name = "password")
private String password;

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}
}
