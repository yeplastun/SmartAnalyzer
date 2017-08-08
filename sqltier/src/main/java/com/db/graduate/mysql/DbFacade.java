package com.db.graduate.mysql;

import com.db.graduate.dao.Counterparty;
import com.db.graduate.dao.Deal;
import com.db.graduate.dao.Instrument;
import com.db.graduate.util.PropertyLoader;
import com.mysql.cj.mysqla.io.DebugBufferingPacketReader;

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

    @Deprecated
    public DbResponseCode login(String userId, String userPwd) {
        try {
            assert isConnected();

            return loginHandler.login(connection, userId, userPwd);
        } catch (SQLException e) {
            return LOGIN_FAILED;
        }
    }

    public String getPasswordByUserId(String userId) throws SQLException {
        assert isConnected();

        String password = null;
        String sql = "SELECT user_pwd FROM users WHERE user_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userId);
        if (statement.execute()) {
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                password = rs.getString("user_pwd");
            }
        }

        return password;
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
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                Instrument instrument = new Instrument();
                instrument.setInstrumentId(rs.getLong("instrument_id"));
                instrument.setInstrumentName(rs.getString("instrument_name"));

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
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                Counterparty counterparty = new Counterparty();
                counterparty.setCounterpartyId(rs.getLong("counterparty_id"));
                counterparty.setCounterpartyName(rs.getString("counterparty_name"));
                counterparty.setCounterpartyStatus(rs.getString("counterparty_status"));
                counterparty.setCounterpartyDateRegistered(rs.getDate("counterparty_date_registered"));

                counterparties.add(counterparty);
            }
        } else {
            throw new SQLException("Unable to get all instruments from database");
        }

        statement.close();
        return counterparties;
    }

    public List<Deal> getAllDeals() throws SQLException {
        assert isConnected();

        List<Deal> deals = new ArrayList<>();
        Statement statement = connection.createStatement();
        if (statement.execute("SELECT * FROM deal")) {
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                Deal deal = new Deal();
                deal.setDealId(rs.getLong("deal_id"));
                deal.setDealTime(rs.getString("deal_time"));
                deal.setDealCounterpartyId(rs.getLong("deal_counterparty_id"));
                deal.setDealInstrumentId(rs.getLong("deal_instrument_id"));
                deal.setDealType(rs.getString("deal_type"));
                deal.setDealAmount(rs.getDouble("deal_amount"));
                deal.setDealQuantity(rs.getInt("deal_quantity"));

                deals.add(deal);
            }
        } else {
            throw new SQLException("Unable to get all instruments from database");
        }

        statement.close();
        return deals;
    }
}
