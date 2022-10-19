package com.paymybuddy.controller;




import com.paymybuddy.dto.BankDto;
import com.paymybuddy.dto.UserDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnStatusOkForContact() throws Exception {
        // then
        mockMvc.perform(get("/contact"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @WithMockUser
    @Test
    public void shouldSaveAContact() throws Exception {

        //given
        UserDto userDto = new UserDto(1, "eren", "jager", "eren@email.com", "1234", 100.0, "CIC");
        BankDto bankDto = new BankDto(1, "CIC");


        //then
        //mockMvc.perform(post("/contact")
         //         .flashAttr("contact"))
        //.andDo(MockMvcResultHandlers.print())
        //.andExpect(status().isFound())
        //.andExpect(redirectedUrl("/transaction"));
    }
}