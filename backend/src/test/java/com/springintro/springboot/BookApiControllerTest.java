package com.springintro.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springintro.springboot.controller.BookController;
import com.springintro.springboot.model.BookModel;
import com.springintro.springboot.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookController.class)
public class BookApiControllerTest {
    private static final String END_POINT_PATH = "/api";

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private BookService bookService;

    @Test
    public  void testAddShouldReturn200Success() throws  Exception{
        BookModel newBook = new BookModel(2,"me", 2010, 200, "Fahad","ghost", "ltd");

        mockMvc.perform(post("/api/books/")
                        .content(objectMapper.writeValueAsString(newBook))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());

    }

}
