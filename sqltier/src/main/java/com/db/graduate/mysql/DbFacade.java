package com.db.graduate.mysql;

import com.db.graduate.util.PropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import static com.db.graduate.mysql.DbResponseCode.FAILED;
import static com.db.graduate.mysql.DbResponseCode.SUCCESSFUL;

public class DbFacade {

    private static int TIMEOUT_IN_SECONDS = 5;

    private Connection connection;

    public DbResponseCode connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties properties = PropertyLoader.loadProperties();
            String dbUrl = properties.getProperty("dbPath") + properties.getProperty("dbName");
            String dbUser = properties.getProperty("dbUser");
            String dbPwd = properties.getProperty("dbPwd");

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);

            return SUCCESSFUL;
        } catch (InvalidPropertiesFormatException | SQLException e) {
            return FAILED;
        }
    }

    public boolean isConnected() throws SQLException {
        return connection != null && connection.isValid(TIMEOUT_IN_SECONDS);
    }

    public boolean isClosed() throws SQLException {
        return connection != null && connection.isClosed();
    }

    public void disconnect() throws SQLException {
        if (connection != null) connection.close();
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }
}
