package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.TaskRepository;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
    private final TaskRepository taskRepository;

    public TaskServlet() {
        this.taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = String.valueOf(req.getSession().getAttribute("username"));
        Set<String> tasks = taskRepository.getTaskListByUsername(username);
        if (tasks == null) {
            tasks = new LinkedHashSet<>();
        }
        if (req.getParameter("task") != null) {
            tasks.add(req.getParameter("task"));
        }
        if (req.getParameter("removeTask") != null) {
            tasks.remove(req.getParameter("removeTask"));
        }
        boolean isUpdated = taskRepository.updateTaskListByUsername(username, tasks);
        if (!isUpdated) {
            req.setAttribute("warnMessage", "Task not updated!");
            getServletContext().getRequestDispatcher("/WEB-INF/pages/todo.jsp").forward(req, resp);
        }
        req.setAttribute("tasks", tasks);
        getServletContext().getRequestDispatcher("/WEB-INF/pages/todo.jsp").forward(req, resp);
    }
}
