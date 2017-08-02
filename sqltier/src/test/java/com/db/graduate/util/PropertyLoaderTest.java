package com.db.graduate.util;

import org.junit.Test;

import java.util.Properties;

public class PropertyLoaderTest {
    @Test
    public void loadPropertiesTest() throws Exception {
        // Given, when
        Properties properties = PropertyLoader.loadProperties();

        // Then
        assert properties.getProperty("dbDriver").equals("com.mysql.jdbc.Driver");
        assert properties.getProperty("dbPath").equals("jdbc:mysql://localhost/");
        assert properties.getProperty("dbName").equals("db_grad_cs_1917");
        assert properties.getProperty("dbUser").equals("root");
        assert properties.getProperty("dbPwd").equals("");
    }
}