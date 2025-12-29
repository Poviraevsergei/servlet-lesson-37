package util;

public interface SQLCommands {
    String GET_ALL_USERS = "SELECT * FROM users";
    String GET_USER_BY_PASSWORD = "SELECT * FROM users WHERE password=?";
    String CREATE_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";
    String GET_TASKS_BY_USERNAME = "SELECT value FROM tasks WHERE user_id=(SELECT id FROM users WHERE username=?);";
    String ADD_TASK_BY_USERNAME = "INSERT INTO tasks (value, user_id) VALUES (?, (SELECT id FROM users WHERE username=?))";
}
