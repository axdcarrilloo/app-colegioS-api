package com.casa.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarTodosTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/Colegios/Rol/Todos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpectAll(MockMvcResultMatchers.status().isOk(), MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

}
