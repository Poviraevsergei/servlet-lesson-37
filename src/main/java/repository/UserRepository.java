package repository;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, String> userList;

    public UserRepository() {
        userList = new HashMap<>();
        userList.put("admin", "admin");
        userList.put("user", "user");
    }

    public boolean isPasswordContains(String password) {
        return userList.containsValue(password);
    }

    public String getUsernameByPassword(String password) {
        return userList.get(password);
    }
}
