package com.rsn.webquizengine.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenAddQuizIsInvalid_thenReturnsStatus400() throws Exception {
//        assertEquals("", result.getResponse().getContentAsString());
//        Input input = invalidInput();
        mvc.perform(
                post("")
                        .contentType("application/json")
//                .content(objectMapper.writeValueAsString)
        );
    }

    @Test
    void postAnswer() {
    }
}