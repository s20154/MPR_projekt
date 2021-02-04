package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.repository.ClientRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private BookService bookService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void findAll() {
        when(clientRepository.findAll())
                .thenReturn(List.of(new Client()));
        List<Client> all = clientService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void deleteById() {
        clientService.deleteById(1L);
        clientService.deleteById(2L);
        verify(clientRepository, times(2)).deleteById(anyLong());
    }

    @Test
    void save(){
        //given
        Client client = new Client("Damian","Kijanczuk","111222333",23);
        when(clientRepository.save(client))
                .thenReturn(new Client("Damian","Kijanczuk","111222333",23));
        //when
        Client save = clientRepository.save(client);
        //then
        assertThat(save.getSurname()).isEqualTo("Kijanczuk");
    }

}
