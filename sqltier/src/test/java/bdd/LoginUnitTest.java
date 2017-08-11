package bdd;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.db.graduate.mysql.DbResponseCode;
import static com.db.graduate.mysql.DbResponseCode.LOGIN_FAILED;
import static com.db.graduate.mysql.DbResponseCode.LOGIN_SUCCESSFUL;
import com.db.graduate.mysql.LoginHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.jmock.AbstractExpectations.returnValue;
import org.jmock.Expectations;
import org.jmock.Mockery;
import com.db.graduate.mysql.LoginHandler;
import static org.jmock.AbstractExpectations.returnValue;
import static org.junit.Assert.assertNotNull;

public class LoginUnitTest {
	
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
            oneOf(connection).createStatement();
            will(returnValue(statement));
            oneOf(statement).execute(loginHandler.getPasswordSql(userId));
            will(returnValue(true));
            oneOf(statement).getResultSet();
            will(returnValue(resultSet));
        }});
     assertNotNull(connection) ;
        assertNotNull(loginHandler);  
 
        }
    @Test
    public void loginSucceedTest() throws Exception {
        context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(true));
            oneOf(resultSet).next();
            will(returnValue(false));
            oneOf(resultSet).getString("user_pwd");
            will(returnValue(userPwd));
        }});

        DbResponseCode response = loginHandler.login(connection, userId, userPwd);
        assert response == LOGIN_SUCCESSFUL;
	}

	
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
	

	
	


