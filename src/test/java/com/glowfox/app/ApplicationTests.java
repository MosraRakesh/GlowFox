package com.glowfox.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.glowfox.app.controller.ClassControllerTest;

import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationTests {
	
	
	private ClassControllerTest classtest=new ClassControllerTest();

    @Test
    void contextLoads() {
    	//classtest.createClass_shouldReturnCreatedClass();
    }
}
