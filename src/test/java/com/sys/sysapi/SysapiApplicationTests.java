package com.sys.sysapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysapiApplicationTests {

    @Test
    public void contextLoads() {
        String usrHome = System.getProperty("user.home");

    }

}
