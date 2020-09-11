package openevents.ejb.users;


import openevents.models.UserModel;
import org.apache.commons.lang3.StringUtils;

import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public UserModel registerUser(UserModel user) throws Exception {
        if (user == null)
            throw new Exception("No user details");

        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword()))
            throw new Exception("Invalid user details!!");


        return em.merge(user);
    }

    public UserModel authenticate(String email , String password) throws Exception{

        UserModel user = null;
        String query = "SELECT u FROM UserModel u WHERE u.email=:email";
        try{
            user = (UserModel) this.em.createQuery(query)
                    .setParameter("email", email).getSingleResult();
        }catch(NoResultException noResultException){
            throw new Exception("Wrong email or password");
        }

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("login successful");
           return user;
        }else{
            throw new Exception("Wrong email or password");
        }

    }
}
