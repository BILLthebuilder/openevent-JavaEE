package openevents.controllers.users;

import database.HibernateHelper;
import openevents.beans.UserBean;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/register"})
public class UsersController {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("repeat_email");
        String plainPassword = request.getParameter("repeat_password");

        String password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());


        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();

            UserBean user = new UserBean();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            session.save(user);

            response.getWriter().println("Registration is successful!!");
            tx.commit();
        } catch (Exception e) {
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }

    }
    
}
