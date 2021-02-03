package pl.pjatk.gameplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Book;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.service.BookService;
import pl.pjatk.gameplay.service.ClientService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private ClientService clientService;

    public BookController(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to client site");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> optionalPlayer = bookService.findByID(id);

        if (optionalPlayer.isPresent()) {
            return ResponseEntity.ok(optionalPlayer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.update(book));
    }

    @GetMapping("/{id}/isOverdue")
    public ResponseEntity<Boolean> isOverdue(@PathVariable Long id) {
        // Does book exist
        if (bookService.findByID(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Client> optionalClient = clientService
                .findAll().stream()
                .filter(c -> c.getOwnedBook() == id).findFirst();
        // Does player exist
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalClient.get().getBorrowDate().plusWeeks(2).isAfter(LocalDateTime.now()));
    }


}
