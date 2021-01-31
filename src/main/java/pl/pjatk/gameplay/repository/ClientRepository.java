package pl.pjatk.gameplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.gameplay.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
