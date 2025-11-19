import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bye-page")
public class ByebyeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String age = req.getParameter("age");
        String name = req.getParameter("name"); //TODO: не путать с методом req.getAttribute()

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Byebye " + name + " from ByebyeServlet. Your age " + age + " </h1>");
    }
}
