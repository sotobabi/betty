package com.codingnomads.betty.presentation.controller;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OddsByMatchControllerTest {

    private OddsByMatchController testController;

    @Before
    public void setUp() {
        testController = new OddsByMatchController();
    }

    @Test
    public void whenGetOddsByMatchIsCalled_oddsByMatchIsReturned() {
        assertThat(testController.getOddsByMatch()).isEqualTo("odds-by-match");
    }
}
