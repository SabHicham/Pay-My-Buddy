package com.paymybuddy.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class BankControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser
    @Test
    public void shouldReturnStatusOkForBankRegistration() throws Exception {
        // then
        mockMvc.perform(get("/bankregistration"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("bankregistration"));
    }

    @WithMockUser
    @Test
    public void shouldSaveABank() throws Exception {

        // given
        BankDto bankDto = new BankDto(1, "CIC");

        //then
        mockMvc.perform(post("/bankregistration")
                        .flashAttr("bankregistration", bankDto))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/home"));
    }
}