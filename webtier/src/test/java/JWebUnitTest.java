/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.awt.Button;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*; 


import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;

public class JWebUnitTest {

    @Before
    public void prepare() {
        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT); 

        setBaseUrl("http://localhost:8084/webapp/");
    }

    @Test
    public void testLogin() {
        beginAt("index.jsp");
        assertTitleEquals("Login");
        setTextField("username", "test");
        setTextField("password", "test123");
        submit();
    }
    @Test
    public void testSubmit(){
        beginAt("index.jsp");
        assertButtonPresent("submit_button");
    }
    }

