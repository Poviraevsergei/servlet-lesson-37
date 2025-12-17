package repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static UserRepository instance;
    private final Map<String, String> userList;

    private UserRepository() {
        userList = new HashMap<>();
        userList.put("admin", "admin");
        userList.put("user", "user");
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public boolean isUsernameContains(String username) {
        return userList.containsKey(username);
    }

    public boolean isPasswordContains(String password) {
        return userList.containsValue(password);
    }

    public String getUsernameByPassword(String password) {
        return userList.get(password);
    }
}
