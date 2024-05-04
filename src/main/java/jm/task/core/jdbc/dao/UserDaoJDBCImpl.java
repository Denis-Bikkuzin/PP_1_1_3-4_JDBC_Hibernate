package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = new Util().getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ( " + "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " + "name VARCHAR(40), " + "lastname VARCHAR(40), " + "age TINYINT)");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void dropUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name , lastname, age) VALUES(?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT name, lastname, age from users";
        List<User> users = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                byte age = rs.getByte("age");
                users.add(new User(name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
