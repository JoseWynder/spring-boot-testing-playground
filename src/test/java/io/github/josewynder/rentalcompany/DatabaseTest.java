package io.github.josewynder.rentalcompany;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute(" CREATE TABLE users (id INT, name VARCHAR) ");
    }

    @BeforeEach
    void insertUser() throws Exception {
        connection.createStatement().execute( "insert into users(id, name) values (1, 'José') ");
    }

    @Test
//    @Disabled
    void testUsersExists() throws Exception {
        ResultSet resultSet = connection
                .createStatement()
                .executeQuery("select * from users where id = 1");

        Assertions.assertTrue(resultSet.next());
    }

    @AfterAll
    static void closeDatabase() throws Exception {
        connection.close();
    }
}
