package com.diadiushko.cli.services;

import com.diadiushko.entities.User;
import com.diadiushko.enums.CliMessages;
import com.diadiushko.enums.LoggerMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class DatabaseService {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/test-project";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String USER_INSERT_SQL_STATEMENT = "INSERT INTO users (id, name, age) VALUES (?,?,?)";
    private static final String CONSTRAINT_KEY_VIOLATION_SQL_CODE = "23505";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
    private static Connection connection;

    public static void setConnection(boolean reconnectIfFail) {
        logger.info(LoggerMessages.SETTING_CONNECTION.getMessage(), JDBC_URL, USERNAME);
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            if (reconnectIfFail) {
                setConnection(true);
            }
        }
    }

    public static boolean insertUser(User user) {
        try {
            if (!connection.isValid(0)) {
                logger.info(LoggerMessages.CONNECTION_IS_ALREADY_CLOSE.getMessage());
                return false;
            }
            PreparedStatement statement = connection.prepareStatement(USER_INSERT_SQL_STATEMENT);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getAge());

            int rowsAltered = statement.executeUpdate();
            return (rowsAltered > 0);
        } catch (SQLException e) {
            if (e.getSQLState().equals(CONSTRAINT_KEY_VIOLATION_SQL_CODE)) {
                System.out.println(CliMessages.USER_ALREADY_EXISTS);
            }
            logger.error(e.getMessage());
            return false;
        }
    }
}
