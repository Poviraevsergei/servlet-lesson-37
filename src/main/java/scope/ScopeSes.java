package scope;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/ses-scope")
public class ScopeSes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nameAttribute = String.valueOf(session.getAttribute("name"));
        System.out.println(nameAttribute);
        session.setAttribute("name", "Dima");
        System.out.println(session.getAttribute("name"));
    }
}
