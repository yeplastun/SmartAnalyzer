package bdd;

import org.junit.Before;
import org.junit.Test;

import com.db.graduate.mysql.DbFacade;
import java.sql.Connection;
import java.sql.SQLException;
import org.jmock.Expectations;
import org.jmock.Mockery;



public class dbConnectionUnitTest {
	
	DbFacade DbFacade1= new DbFacade();	
	String username1,pwd1;
          private Mockery context = new Mockery();

    private DbFacade dbFacade;
    private Connection connection;
	

	@Before
	public void setUp() throws Exception {
                    dbFacade = new DbFacade();
        connection = context.mock(Connection.class);
        dbFacade.setConnection(connection);
	}

	
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
	        context.checking(new Expectations() {{
            oneOf(connection).close();
        }});

        // execute
        dbFacade.disconnect();

        // verify
        context.assertIsSatisfied();
		
	}
	

	
	

}
