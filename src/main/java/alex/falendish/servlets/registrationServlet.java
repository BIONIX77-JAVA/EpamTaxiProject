package alex.falendish.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registrationServlet")
public class registrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");


        if(first_name.isEmpty() || last_name.isEmpty() || username.isEmpty() ||
                password.isEmpty())
        {
            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/jsp/register_main.jsp");
            req.include(request, response);
        }
        else {
            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/jsp/sign_if_registered.jsp");
            req.forward(request, response);
        }
    }
}