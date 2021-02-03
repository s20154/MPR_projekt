package pl.pjatk.gameplay.service;


import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Book;
import pl.pjatk.gameplay.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book update(Book book) {
        Optional<Book> byId = bookRepository.findById(book.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException();
        } else {
            return bookRepository.save(book);
        }
    }

    public void deleteAll() {
        bookRepository.deleteAll();
    }

}
