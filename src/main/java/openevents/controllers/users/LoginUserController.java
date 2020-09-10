package openevents.controllers.users;

import database.HibernateHelper;
import openevents.models.UserModel;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
@SuppressWarnings({"unchecked"})
public class LoginUserController extends HttpServlet {

    public boolean authenticate(String email , String password){
        Session session = HibernateHelper.getSessionFactory().openSession();
        UserModel user = null;
        user = (UserModel) session.createQuery("SELECT u FROM UserModel u WHERE u.email=:email")
                .setParameter("email", email).uniqueResult();
                //.setParameter("pwd", password);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("login successful");
            return true;
        }else{
            //throw new Exception("Invalid user details");
            System.out.println("invalid user details");
            return false;
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("user_email");
        String plainPassword = request.getParameter("repeat_password");

        try {

            if (authenticate(email,plainPassword)) {
                //HttpSession httpSession = request.getSession();
                //httpSession.setAttribute("name", name);
                //httpSession.setAttribute("email", email);
                //httpSession.setAttribute("password", plainPassword);
               //httpSession.setAttribute("role", role);
                response.getWriter().println("authentication_success");
            } else response.getWriter().println("authentication_failed");


            //response.getWriter().println("Registration is successful!!");

        } catch (Exception e) {
            //response.getWriter().println("An error has occurred");
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        }

    }
}
