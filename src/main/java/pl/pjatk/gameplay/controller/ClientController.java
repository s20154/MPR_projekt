package pl.pjatk.gameplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Book;
import pl.pjatk.gameplay.model.Client;
import pl.pjatk.gameplay.repository.BookRepository;
import pl.pjatk.gameplay.service.BookService;
import pl.pjatk.gameplay.service.ClientService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;
    private BookService bookService;

    public ClientController(ClientService clientService, BookService bookService) {
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to client site");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Optional<Client> optionalPlayer = clientService.findByID(id);

        if (optionalPlayer.isPresent()) {
            return ResponseEntity.ok(optionalPlayer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.update(client));
    }

    @PutMapping("/{id}/giveBook/{bookId}")
    ResponseEntity<Client> giveBook(@PathVariable Long id, @PathVariable Long bookId) {
        Optional<Client> optionalClient = clientService.findByID(id);
        LocalDateTime date = LocalDateTime.now();

        // Does client alredy have a book
        if (!(optionalClient.get().getOwnedBook() == null)) {
            return ResponseEntity.badRequest().build();
        }
        // Does book exist
        if (bookService.findByID(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        optionalClient.get().setOwnedBook(bookId);
        optionalClient.get().setBorrowDate(date);
        return ResponseEntity.ok(clientService.update(optionalClient.get()));
    }

    @PutMapping("/{id}/returnBook")
    ResponseEntity<Client> returnBook(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.findByID(id);

        // Does client REALLY have a book
        if (optionalClient.get().getOwnedBook() == null) {
            return ResponseEntity.badRequest().build();
        }

        optionalClient.get().setOwnedBook(null);
        optionalClient.get().setBorrowDate(null);
        return ResponseEntity.ok(clientService.update(optionalClient.get()));
    }


    @GetMapping("/{id}/ownedBook")
    public ResponseEntity<Book> showOwnedBook(@PathVariable Long id) {
        Optional<Client> optionalPlayer = clientService.findByID(id);
        Long bookId;

        // Does player exist
        if (!optionalPlayer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Does he own a book
        if (optionalPlayer.get().getOwnedBook() == null) {
            return ResponseEntity.notFound().build();
        }

        bookId = optionalPlayer.get().getOwnedBook();
        Optional<Book> optionalBook = clientService.showOwnedBook(bookId);
        return ResponseEntity.ok(optionalBook.get());
    }

    @GetMapping("/{id}/isBookOverdue")
    public ResponseEntity<Boolean> isOverdue(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.findByID(id);

        // Does player exist
        if (!optionalClient.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Does he own a book
        if (optionalClient.get().getOwnedBook() == null) {
            System.out.println("elo");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalClient.get().getBorrowDate().plusWeeks(2).isBefore(LocalDateTime.now()));
    }

}
