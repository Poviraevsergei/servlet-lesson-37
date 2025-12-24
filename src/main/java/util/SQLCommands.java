package util;

public interface SQLCommands {
    String GET_ALL_USERS = "SELECT * FROM users";
    String GET_USER_BY_PASSWORD = "SELECT * FROM users WHERE password=?";
    String CREATE_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
}
