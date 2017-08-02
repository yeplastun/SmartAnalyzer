package com.db.graduate.mysql;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class MySqlFacadeTest {
    private Mockery context = new Mockery();

    private MySqlFacade mySqlFacade;
    private Connection connection;

    @Before
    public void setUp() {
        mySqlFacade = new MySqlFacade();
        connection = context.mock(Connection.class);
        mySqlFacade.setConnection(connection);
    }

    @Test
    public void closeTest() throws Exception {
        // expectations
        context.checking(new Expectations() {{
            oneOf(connection).close();
        }});

        // execute
        mySqlFacade.disconnect();

        // verify
        context.assertIsSatisfied();
    }

}