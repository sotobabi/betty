package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTests {

    private HelloController testController;

    @Before
    public void setUp() {
        testController = new HelloController();
    }

    @Test
    public void whenRootMappingIsCalled_helloTemplateIsReturned() {
        assertThat(testController.sayHello().contains("hello")).isTrue();
    }


}
