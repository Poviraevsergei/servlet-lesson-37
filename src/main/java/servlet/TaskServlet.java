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
    private Boolean isUpdated;

    public TaskServlet() {
        this.taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = String.valueOf(req.getSession().getAttribute("username"));
        if (req.getParameter("task") != null) {
            isUpdated = taskRepository.addTaskByUsername(username, req.getParameter("task"));
        }
        //if (req.getParameter("removeTask") != null) { TODO: remove
        //    tasks.remove(req.getParameter("removeTask"));
        //}
        if (!isUpdated) {
            req.setAttribute("warnMessage", "Task not updated!");
            getServletContext().getRequestDispatcher("/WEB-INF/pages/todo.jsp").forward(req, resp);
        }
        req.setAttribute("tasks", taskRepository.getTaskListByUsername(username));
        getServletContext().getRequestDispatcher("/WEB-INF/pages/todo.jsp").forward(req, resp);
    }
}
