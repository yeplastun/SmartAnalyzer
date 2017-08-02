package com.db.graduate.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.db.graduate.mysql.MySqlResponse.FAILED;
import static com.db.graduate.mysql.MySqlResponse.SUCCESS;

public class MySqlFacade {

    private static int TIMEOUT_IN_SECONDS = 5;

    private Connection connection;

    public MySqlResponse connect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/db_grad_cs_1917",
                    "root", "");

            return SUCCESS;
        } catch (SQLException e) {
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
