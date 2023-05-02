package com.bookcoach.book_coach_be.controller;


import com.bookcoach.book_coach_be.config.JwtService;
import com.bookcoach.book_coach_be.model.HappyStudent;
import com.bookcoach.book_coach_be.service.HappyStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HappyStudentController.class)
@Import(HappyStudentController.class)
@AutoConfigureMockMvc
class HappyStudentControllerTest {

    @MockBean
    private HappyStudentService happyStudentService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username="user@gmail.com", authorities = {"ADMIN"})
    void should_return_happyStudents_list() throws Exception {
        HappyStudent happyStudent = buildTestingHappyStudent();

        when(happyStudentService.getAllHappyStudents()).thenReturn(List.of(happyStudent));

        mockMvc.perform(get("/happy"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect( jsonPath("$[0].description", equalTo("desc")))
                .andExpect( jsonPath("$[0].name", equalTo("Maciek")))
                .andExpect( jsonPath("$[0].imageUrl", equalTo("imageURL")));
    }

    @Configuration
    static class Config {
        @Bean
        public JwtService jwtService() {
            return new JwtService();
        }
    }

    private HappyStudent buildTestingHappyStudent(){
        HappyStudent happyStudent = new HappyStudent();
        happyStudent.setId(1L);
        happyStudent.setDescription("desc");
        happyStudent.setName("Maciek");
        happyStudent.setImageUrl("imageURL");
        return happyStudent;
    }
}