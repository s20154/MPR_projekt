
package pl.pjatk.gameplay.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.repository.ClientRepository;
import pl.pjatk.gameplay.service.ClientService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/client"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldMatchContentForHelloWorld() throws Exception {
        mockMvc.perform(get("/client/welcome"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to client site")));
    }

    @Test
    void shouldNotFindClient() throws Exception {
        mockMvc.perform(get("/player/1000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindClient() throws Exception {
        when(clientService.findByID(ArgumentMatchers.any()))
                .thenReturn(Optional.of(new Client( "Magda", "Gejsler", "111222333", 44)));
        mockMvc.perform(get("/client/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}

