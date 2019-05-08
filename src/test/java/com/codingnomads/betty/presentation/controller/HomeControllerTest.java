package com.codingnomads.betty.presentation.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest {

    private MockMvc mockMvc;
    private HomeController testController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
        testController = new HomeController();
    }

    @Test
    public void whenRootMappingIsCalled_indexTemplateIsReturned() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    public void whenUnknownMappingIsInvoked_notFoundAndNullErrorIsReturned() throws Exception {
        this.mockMvc.perform(get("/random-page")).andExpect(status().isNotFound())
                .andExpect(redirectedUrl(null));
    }

    @Test
    public void whenTheErrorMappingIsInvoked_errorTemplateIsReturned() {
        assertThat(testController.error().contentEquals("error")).isTrue();
    }

    @Test
    public void whenRootMappingIsCalled_helloTemplateIsReturned() {
        assertEquals("index", testController.index());
    }
}