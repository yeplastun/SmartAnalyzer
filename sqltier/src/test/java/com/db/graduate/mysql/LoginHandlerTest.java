package com.db.graduate.mysql;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.db.graduate.mysql.DbResponseCode.LOGIN_FAILED;
import static com.db.graduate.mysql.DbResponseCode.LOGIN_SUCCESSFUL;

public class LoginHandlerTest {
    private Mockery context = new Mockery();

    private String userId = "userId";
    private String userPwd = "userPwd";
    private LoginHandler loginHandler;
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        loginHandler = new LoginHandler();
        resultSet = context.mock(ResultSet.class);
        statement = context.mock(PreparedStatement.class);
        connection = context.mock(Connection.class);

        context.checking(new Expectations() {{
            oneOf(connection).prepareStatement(loginHandler.getPasswordSql());
            will(returnValue(statement));
            oneOf(statement).setString(1, userId);
            oneOf(statement).execute();
            will(returnValue(true));
            oneOf(statement).close();
            oneOf(statement).getResultSet();
            will(returnValue(resultSet));
        }});
    }

    @Test
    public void loginSucceedTest() throws Exception {
        // Given
        context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(true));
            oneOf(resultSet).next();
            will(returnValue(false));
            oneOf(resultSet).getString("user_pwd");
            will(returnValue(userPwd));
        }});

        // When
        DbResponseCode response = loginHandler.login(connection, userId, userPwd);

        // Then
        assert response == LOGIN_SUCCESSFUL;
    }

    @Test
    public void loginFailedDueToIncorrectPasswordTest() throws Exception {
        // Given
        context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(true));
            oneOf(resultSet).next();
            will(returnValue(false));
            oneOf(resultSet).getString("user_pwd");
            will(returnValue(userPwd + "incorrect"));
        }});

        // When
        DbResponseCode response = loginHandler.login(connection, userId, userPwd);

        // Then
        assert response == LOGIN_FAILED;
    }

    @Test
    public void loginFailedDueToAbsentUserTest() throws Exception {
        // Given
        context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(false));
        }});

        // When
        DbResponseCode response = loginHandler.login(connection, userId, userPwd);

        // Then
        assert response == LOGIN_FAILED;
    }
}