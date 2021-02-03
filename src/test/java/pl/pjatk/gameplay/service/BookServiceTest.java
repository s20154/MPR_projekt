package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Book;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.repository.BookRepository;
import pl.pjatk.gameplay.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private ClientService clientService;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void findAll() {
        when(bookRepository.findAll())
                .thenReturn(List.of(new Book()));
        List<Book> all = bookService.findAll();
        assertThat(all).hasSize(1);
    }

    @Test
    void deleteById() {
        bookService.deleteById(1L);
        bookService.deleteById(2L);
        verify(bookRepository, times(2)).deleteById(anyLong());
    }

    @Test
    void save(){
        //given
        Book book = new Book("Podstawy CB radia", "Bajo jajo", "Nufka sztuka szefie");
        when(bookRepository.save(book))
                .thenReturn(new Book("Podstawy CB radia", "Bajo jajo", "Nufka sztuka szefie"));
        //when
        Book save = bookService.save(book);
        //then
        assertThat(save.getAuthor()).isEqualTo("Bajo jajo");
    }

}
