package br.pucrio.ldw.aloLdw21.web;


import br.pucrio.ldw.aloLdw21.model.CommentRepository;
import br.pucrio.ldw.aloLdw21.model.PostRepository;
import br.pucrio.ldw.aloLdw21.model.DAO.UserDAO;
import br.pucrio.ldw.aloLdw21.model.UserRepository;
import br.pucrio.ldw.aloLdw21.service.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MeuController.class)
@AutoConfigureMockMvc
public class MeuControllerWebTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Animal pseudoAnimal;
    @MockBean
    PostRepository postRepository;
    @MockBean
    CommentRepository commentRepository;
    @MockBean
    UserDAO userDAO;
    @MockBean
    UserRepository userRepository;

    @Test
    public void  getMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Hello LDW21!"));
    }
}
