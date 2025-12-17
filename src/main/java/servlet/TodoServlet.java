package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.TaskRepository;

import java.io.IOException;
import java.util.Set;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private final TaskRepository taskRepository;

    public TodoServlet() {
        this.taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = String.valueOf(req.getSession().getAttribute("username"));
        Set<String> tasks = taskRepository.getTaskListByUsername(username);
        req.setAttribute("tasks", tasks);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/todo.jsp").forward(req, resp);
    }
}
