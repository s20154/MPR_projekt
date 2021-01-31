package pl.pjatk.gameplay.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/player"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldMatchContentForHelloWorld() throws Exception {
        mockMvc.perform(get("/player/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world")));
    }

    @Test
    void shouldNotFindPlayer() throws Exception {
        mockMvc.perform(get("/player/1000"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindPlayer() throws Exception {
        when(playerService.findByID(ArgumentMatchers.any()))
                .thenReturn(Optional.of(new Player(1L, "nickname", 100, 10)));
        mockMvc.perform(get("/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"nickname\":\"nickname\",\"health\":100,\"attack\":10}")));
    }
}