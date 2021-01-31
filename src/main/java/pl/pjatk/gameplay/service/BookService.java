package pl.pjatk.gameplay.service;


import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Book;
import pl.pjatk.gameplay.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository clientRepository) {
        this.bookRepository = clientRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findByID(Long playerId) {
        if (playerId == 10L) {
            throw new RuntimeException();
        } else {
            return bookRepository.findById(playerId);
        }
    }

    public Book save(Book client) {
        return bookRepository.save(client);
    }

    public void delete(Book client) {
        bookRepository.delete(client);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book update(Book client) {
        Optional<Book> byId = bookRepository.findById(client.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException();
        } else {
            return bookRepository.save(client);
        }
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

}
