package com.milfist.springbootservice.controller;

import com.milfist.springbootservice.DataApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest(classes = DataApplication.class)
@ActiveProfiles("local")
class ApplicationDataTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void givenCorrectID_whenCallEndPoint_thenOk() throws Exception {
        mockMvc.perform(get("/data/data")
            .contentType("application/json; charset=UTF-8")
            .header("accept", "*/*"))
            .andExpect(status().isOk());
    }

    @Test
    void givenWrongID_whenCallEndPoint_then404() throws Exception {
        mockMvc.perform(get("/data/data2")
            .contentType("application/json; charset=UTF-8")
            .header("accept", "*/*"))
            .andExpect(status().isNotFound());
    }

    @Test
    void givenCorrectID_but_empty_file_whenCallEndPoint_then204() throws Exception {
        mockMvc.perform(get("/data/data_null")
            .contentType("application/json; charset=UTF-8")
            .header("accept", "*/*"))
            .andExpect(status().isNoContent());
    }
}
