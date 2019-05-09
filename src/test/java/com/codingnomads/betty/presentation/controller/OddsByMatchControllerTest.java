package com.codingnomads.betty.presentation.controller;

import com.codingnomads.betty.logic.services.GameInformationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class OddsByMatchControllerTest {

    private OddsByMatchController testController;
    private GameInformationService mockGameInformationService;
    private Model mockModel;

    @Before
    public void setUp() {
        mockGameInformationService = mock(GameInformationService.class);
        mockModel = mock(Model.class);
        testController = new OddsByMatchController(mockGameInformationService);
    }

    @Test
    public void whenGetOddsByMatchIsCalled_oddsByMatchIsReturned() {
        assertThat(testController.getOddsByMatch(mockModel)).isEqualTo("odds-by-match");
    }
}
