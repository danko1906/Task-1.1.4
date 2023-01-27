package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement st = connection.createStatement()) { // Создание Statement для отправки запроса базе данных
            String SQLCommand = "CREATE TABLE IF NOT EXISTS users_db (" +
                    "id SERIAL PRIMARY KEY NOT NULL, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "lastName VARCHAR(255) NOT NULL, " +
                    "age INT NOT NULL )"; // переменная с командой sql
            st.executeUpdate(SQLCommand);
            System.out.println("Table created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = connection.createStatement()) { // Создание Statement для отправки запроса базе данных
            String SQLCommand = "DROP TABLE IF EXISTS users_db";
            st.executeUpdate(SQLCommand); // переменная с командой sql
            System.out.println("Table dropped!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { // Создание Statement для отправки запроса базе данных
        try (Statement st = connection.createStatement()) {
            String SQLCommand = "INSERT INTO users_db (name, lastName, age) VALUES ('"
                    + name + "', '" + lastName + "', " + age + ")"; // переменная с командой sql
            st.executeUpdate(SQLCommand);
            System.out.println("User with name - " + name + " added to database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement st = connection.createStatement()) { // Создание Statement для отправки запроса базе данных
            String SQLCommand = "DELETE FROM users_db WHERE id = " + id; // переменная с командой sql
            st.executeUpdate(SQLCommand);
            System.out.println("User with id - " + id + " removed from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List <User> allUsers = new ArrayList();
        try (Statement statement = connection.createStatement()) { // Создание Statement для отправки запроса базе данных
            String SQLCommand = "SELECT * FROM users_db"; // переменная с командой скл
            ResultSet rs = statement.executeQuery(SQLCommand); // результирующий запрос
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getLong(1));
                us.setName(rs.getString(2));
                us.setLastName(rs.getString(3));
                us.setAge(rs.getByte(4));
                allUsers.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() { // Создание Statement для отправки запроса базе данных
        try (Statement st = connection.createStatement()) {
            String SQLCommand = "DELETE FROM users_db"; // переменная с командой sql
            st.executeUpdate(SQLCommand);
            System.out.println("Table cleared!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
