package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("HELLO_SERVLET_CHECK_SCOPE: " + getServletContext().getAttribute("name"));
        Writer writer = resp.getWriter();
        writer.write("Hello World \n");
        writer.write("This is from servlet.HelloServlet");
        writer.close();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("This is init method in servlet.HelloServlet!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("This is service method in servlet.HelloServlet!");
    }

    @Override
    public void destroy() {
        System.out.println("This is destroy method in servlet.HelloServlet!");
    }
}




























