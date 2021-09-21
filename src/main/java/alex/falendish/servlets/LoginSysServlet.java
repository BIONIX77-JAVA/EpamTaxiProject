package alex.falendish.servlets;

//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "LoginSysServlet", value = "/LoginSysServlet")
//public class LoginSysServlet extends HttpServlet {
//    public LoginSysServlet() {
//        super();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        if (username.isEmpty() || password.isEmpty()) {
//            RequestDispatcher req = request.getRequestDispatcher("register_3.jsp");
//            req.include(request, response);
//        } else {
//            RequestDispatcher req = request.getRequestDispatcher("register_4.jsp");
//            req.forward(request, response);
//        }
//    }
//}