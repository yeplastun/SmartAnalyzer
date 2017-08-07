package com.db.graduate.mysql;

import com.db.graduate.dao.Counterparty;
import com.db.graduate.dao.Instrument;
import com.db.graduate.util.PropertyLoader;
import org.w3c.dom.css.Counter;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static com.db.graduate.mysql.DbResponseCode.*;

public class DbFacade {
    static int TIMEOUT_IN_SECONDS = 5;

    private Connection connection;
    private LoginHandler loginHandler;

    public DbFacade() {
        loginHandler = new LoginHandler();
    }

    public DbResponseCode connectToDb() {
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
        } catch (ClassNotFoundException ex) {
            return DRIVER_NOT_FOUND;
        }
    }

    public DbResponseCode login(String userId, String userPwd) {
        try {
            assert isConnected();

            return loginHandler.login(connection, userId, userPwd);
        } catch (SQLException e) {
            return LOGIN_FAILED;
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && connection.isValid(TIMEOUT_IN_SECONDS);
        } catch (SQLException ex) {
            return false;
        }
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

    public List<Instrument> getAllInstruments() throws SQLException {
        assert isConnected();

        List<Instrument> instruments = new ArrayList<>();
        Statement statement = connection.createStatement();
        if (statement.execute("SELECT * FROM instrument")) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Instrument instrument = new Instrument();
                instrument.setInstrumentId(resultSet.getLong("instrument_id"));
                instrument.setInstrumentName(resultSet.getString("instrument_name"));

                instruments.add(instrument);
            }
        } else {
            throw new SQLException("Unable to get all instruments from database");
        }

        statement.close();
        return instruments;
    }

    public List<Counterparty> getAllCounterparties() throws SQLException {
        assert isConnected();

        List<Counterparty> counterparties = new ArrayList<>();
        Statement statement = connection.createStatement();
        if (statement.execute("SELECT * FROM counterparty")) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Counterparty counterparty = new Counterparty();
                counterparty.setCounterpartyId(resultSet.getLong("counterparty_id"));
                counterparty.setCounterpartyName(resultSet.getString("counterparty_name"));
                counterparty.setCounterpartyStatus(resultSet.getString("counterparty_status"));
                counterparty.setCounterpartyDateRegistered(resultSet.getDate("counterparty_date_registered"));

                counterparties.add(counterparty);
            }
        } else {
            throw new SQLException("Unable to get all instruments from database");
        }

        statement.close();
        return counterparties;
    }
}
