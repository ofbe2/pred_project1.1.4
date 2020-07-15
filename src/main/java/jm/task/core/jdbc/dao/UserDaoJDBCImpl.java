package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Statement statement;

    {
        try {
            statement = util.makeConnect().createStatement();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement.executeUpdate(
                    "CREATE TABLE user" +
                            "(Id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                            "Name VARCHAR(20) NOT NULL, " +
                            "lastName VARCHAR(20) NOT NULL, " +
                            "Age INTEGER NOT NULL )"
            );
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS user ");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT user (name, lastName, Age) Values (?, ?, ? )";
        try {
            PreparedStatement preparedStatement = util.makeConnect().prepareStatement(sqlCommand);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM user WHERE" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        User user;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM user ");
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM user ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
