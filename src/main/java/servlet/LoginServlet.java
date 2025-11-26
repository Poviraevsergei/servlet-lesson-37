package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Validator;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Validator validator;

    public LoginServlet() {
        this.validator = new Validator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (validator.validateLoginPassword(username, password)) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect(req.getContextPath()+"/todo");
        } else {
            getServletContext().getRequestDispatcher("/login.html").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.html").forward(req, resp);
    }
}
