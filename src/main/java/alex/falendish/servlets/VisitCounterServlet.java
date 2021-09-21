package alex.falendish.servlets;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VisitCounterServlet", value = "/VisitCounterServlet")
public class VisitCounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        if (username == null) {
            printWriter.write("Hello, Anonymous" + " ");
        } else {
            printWriter.write("Hello, " + username + " ");
        }
        printWriter.write("Page was visited " + visitCounter + " times.");
        printWriter.close();
    }
}