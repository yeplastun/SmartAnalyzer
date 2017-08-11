package bdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.db.graduate.mysql.DbFacade;
import com.db.graduate.mysql.DbResponseCode;
import com.db.graduate.mysql.LoginHandler;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class display_tbls_steps {
	
	LoginHandler handler= new LoginHandler();
	DbResponseCode checkConnection,checkCredentials;
	DbFacade DbFacade1= new DbFacade();
	String username1,pwd1;
	
	@Given("^the login has been successful$")
	public void the_login_has_been_successful() throws Throwable {
		assertNotNull(DbFacade1);
		assertNotNull(handler);
	}

	@When("^the user clicks on submit button$")
	public void the_user_clicks_on_submit_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^user can see the tables in the database\\.$")
	public void user_can_see_the_tables_in_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}


}
