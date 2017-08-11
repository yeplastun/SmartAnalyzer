
package bdd;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.SQLException;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginSteps {
    
    private Mockery context = new Mockery();
    private String userId = "userId";
    private String userPwd = "userPwd";
    private LoginHandler loginHandler= new LoginHandler();;
    private ResultSet resultSet= context.mock(ResultSet.class);
    private PreparedStatement statement = context.mock(PreparedStatement.class);
    private Connection connection= context.mock(Connection.class);
     

	
    @Given("^the database connection has been established$")
    public void the_database_connection_has_been_established() throws Throwable {
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

@When("^the user enters correct user name and password$")
public void the_user_enters_correct_user_name_and_password() throws Throwable {
      context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(true));
            oneOf(resultSet).next();
            will(returnValue(false));
            oneOf(resultSet).getString("user_pwd");
            will(returnValue(userPwd));
        }});
  
}

@Then("^the user can see the message \"([^\"]*)\" displayed on screen\\.$")
public void the_user_can_see_the_message_displayed_on_screen(String arg1) throws Throwable {
   DbResponseCode response = loginHandler.login(connection, userId, userPwd);
  assertEquals(response,LOGIN_SUCCESSFUL); 
}

@Given("^the attempt to establish database connection has been successful$")
public void the_attempt_to_establish_database_connection_has_been_successful() throws Throwable {
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

@When("^the user enters incorrect password$")
public void the_user_enters_incorrect_password() throws Throwable {
 context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(true));
            oneOf(resultSet).next();
            will(returnValue(false));
            oneOf(resultSet).getString("user_pwd");
            will(returnValue(userPwd + "incorrect"));
        }});
}

@Then("^the user sees the message \"([^\"]*)\" displayed on screen\\.$")
public void the_user_sees_the_message_displayed_on_screen(String arg1) throws Throwable {
    DbResponseCode response = loginHandler.login(connection, userId, userPwd);

        // Then
        assert response == LOGIN_FAILED;

}

@Given("^the connection to database has been established$")
public void the_connection_to_database_has_been_established() throws Throwable {
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

@When("^the user enters incorrect user name and password$")
public void the_user_enters_incorrect_user_name_and_password() throws Throwable {
     context.checking(new Expectations() {{
            oneOf(resultSet).next();
            will(returnValue(false));
        }});

}

@Then("^the user can view the message \"([^\"]*)\" on screen\\.$")
public void the_user_can_view_the_message_on_screen(String arg1) throws Throwable {
     DbResponseCode response = loginHandler.login(connection, userId, userPwd);

        // Then
        assert response == LOGIN_FAILED;

}
}
