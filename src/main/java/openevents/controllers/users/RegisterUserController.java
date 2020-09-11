package openevents.controllers.users;

import openevents.ejb.users.UserBean;
import openevents.models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/register"})
public class RegisterUserController extends HttpServlet {
    @EJB
    private UserBean userBean;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("repeat_email");
        String plainPassword = request.getParameter("repeat_password");

        String password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        try {
            UserModel user = new UserModel();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            userBean.registerUser(user);
            response.getWriter().println("Registration is successful!!");
        } catch (Exception e) {
            response.getWriter().println("An error has occurred");
            e.printStackTrace();
        }

    }
    
}
