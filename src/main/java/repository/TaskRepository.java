package repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaskRepository {
    private static TaskRepository instance;
    private final Map<String, Set<String>> taskList;

    private TaskRepository() {
        this.taskList = new HashMap<>();
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    public Set<String> getTaskListByUsername(String username) {
        return taskList.get(username);
    }

    public boolean updateTaskListByUsername(String username, Set<String> taskList) {
        try {
            this.taskList.put(username, taskList);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
