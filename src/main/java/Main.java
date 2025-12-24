import repository.UserRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserRepository userRepository = UserRepository.getInstance();
        System.out.println(userRepository.createUser("testBrokeTransaction", "test"));
    }
}
