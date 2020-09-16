package openevents.controllers.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import openevents.ejb.users.UserBean;
import openevents.models.UserModel;


import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/login"})
@SuppressWarnings({"unchecked"})
public class LoginUserController extends HttpServlet {

    @EJB
    private UserBean userBean;

//    @Inject
//    private UserModel user;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        boolean isLoggedIn = false;
        try {
            HttpSession session = request.getSession(false);
            String email = (String)session.getAttribute("email");
            if( email != null) {
                isLoggedIn = true;
            }
            json.put("email", email);
            json.put("isLoggedIn", isLoggedIn);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);

        }catch(NullPointerException nullPointerException){
            json.put("isLoggedIn",false);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            response.getWriter().println(data);
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String loggedInMessage = "";
        boolean isLoggedIn = true;
        String email = request.getParameter("user_email");
        String plainPassword = request.getParameter("repeat_password");

        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        try {
               userBean.authenticate(email,plainPassword);

                loggedInMessage= "Successful Login";

        } catch (Exception ex) {
            isLoggedIn = false;
            loggedInMessage = ex.getMessage();
            ex.printStackTrace();
        }finally {
            if(isLoggedIn){
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("email", email);
                json.put("authenticated",true);
            }else {
                json.put("authenticated",false);
            }
            json.put("Message",loggedInMessage);
            String data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            writer.println(data);
        }

    }
}
