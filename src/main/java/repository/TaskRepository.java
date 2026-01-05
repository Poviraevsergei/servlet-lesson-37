package repository;

import util.DatabaseConfig;
import util.SQLCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class TaskRepository {
    private static TaskRepository instance;
    private final Connection connection;

    private TaskRepository() {
        connection = DatabaseConfig.getConnection();
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    public Set<String> getTaskListByUsername(String username) {
        Set<String> taskList = new LinkedHashSet<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLCommands.GET_TASKS_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                taskList.add(resultSet.getString(1));
            }
            return taskList;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            throw new RuntimeException(e);
        }
    }

    public boolean addTaskByUsername(String username, String task) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLCommands.ADD_TASK_BY_USERNAME);
            statement.setString(1, task);
            statement.setString(2, username);
            return statement.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeTaskByUsername(String username, String task) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLCommands.REMOVE_TASK_BY_USERNAME_AND_VALUE);
            statement.setString(1, username);
            statement.setString(2, task);
            return statement.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
