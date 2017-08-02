package com.db.graduate.mysql;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class DbFacadeTest {
    private Mockery context = new Mockery();

    private DbFacade dbFacade;
    private Connection connection;

    @Before
    public void setUp() {
        dbFacade = new DbFacade();
        connection = context.mock(Connection.class);
        dbFacade.setConnection(connection);
    }

    @Test
    public void closeTest() throws Exception {
        // expectations
        context.checking(new Expectations() {{
            oneOf(connection).close();
        }});

        // execute
        dbFacade.disconnect();

        // verify
        context.assertIsSatisfied();
    }

}