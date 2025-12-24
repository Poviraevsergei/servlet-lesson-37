package repository;

import exceptions.UserNotFound;
import model.User;
import util.DatabaseConfig;
import util.SQLCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository instance;
    private final Connection connection;

    private UserRepository() {
        connection = DatabaseConfig.getConnection();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public boolean isUsernameContains(String username) {
        //return userList.containsKey(username);
        return false;
    }

    public List<User> getAllUsers() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLCommands.GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getAllUsersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createUser(String username, String password) throws SQLException {
        try {
            connection.setAutoCommit(false);
            //добавляем 1 юзера
            PreparedStatement preparedStatementUser1 = connection.prepareStatement(SQLCommands.CREATE_USER);
            preparedStatementUser1.setString(1, username);
            preparedStatementUser1.setString(2, password);

            int result = preparedStatementUser1.executeUpdate();

            //добавляем 2 юзера
            PreparedStatement preparedStatementUser2 = connection.prepareStatement(SQLCommands.CREATE_USER);
            preparedStatementUser2.setString(1, username);
            preparedStatementUser2.setString(2, password);

            result = preparedStatementUser2.executeUpdate() + result;

            connection.commit();
            return result == 2;
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private List<User> getAllUsersFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

            users.add(user);
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException, UserNotFound {
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        throw new UserNotFound();
    }

    public boolean isPasswordContains(String password) {
        //return userList.containsValue(password);
        return false;
    }

    public User getUserByPassword(String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLCommands.GET_USER_BY_PASSWORD);
            preparedStatement.setString(1, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return getUserFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (UserNotFound e) {
            System.out.println("Username with password " + password + " not found");
            return new User();
        }
    }
}
