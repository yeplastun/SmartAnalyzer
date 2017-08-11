package bdd;

import static org.junit.Assert.assertNotNull;

import com.db.graduate.mysql.DbFacade;
import org.jmock.Mockery;
import java.sql.Connection;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jmock.Expectations;
import static org.junit.Assert.assertEquals;

public class dbConnectionSteps {
     private final Mockery context = new Mockery();

    private DbFacade DbFacade1=new DbFacade();
    private Connection connection= context.mock(Connection.class);

	@Given("^the java code written to connect to the database is working$")
	public void the_java_code_written_to_connect_to_the_database_is_working() throws Throwable {
            
	  assertNotNull(connection) ;
	}

	@When("^the connection has been established$")
	public void the_connection_has_been_established() throws Throwable {
	
        DbFacade1.setConnection(connection);
	}

	@Then("^user can see the message \"([^\"]*)\" displayed on screen\\.$")
	public void user_can_see_the_message_displayed_on_screen(String arg1) throws Throwable {
	   // expectations
        context.checking(new Expectations() {{
            oneOf(connection).close();
        }});

        // execute
        DbFacade1.disconnect();

        // verify
        context.assertIsSatisfied();
	}
}
