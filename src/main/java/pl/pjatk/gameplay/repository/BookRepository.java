package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
