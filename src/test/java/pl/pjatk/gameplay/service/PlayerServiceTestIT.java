package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class PlayerServiceTestIT {

    @Autowired
    private PlayerService playerService;

    @AfterEach
    void cleanUp() {
        playerService.deleteAll();
    }

    @Test
    void shouldNotFindAnyone() {
        List<Player> all = playerService.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    void shouldFindAllPlayers() {
        playerService.save(new Player("nickname", 100, 10));
        List<Player> all = playerService.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldSavePlayer() {
        Player player = playerService.save(new Player("nickname", 100, 10));
        assertThat(player.getId()).isPositive();
    }

    @Test
    void shouldFindPlayerByID() {
        Player player = playerService.save(new Player("nickname", 100, 10));

        assertThat(playerService.findByID(player.getId())).isNotEmpty();
    }

    @Test
    void shouldThrowExceptionOnFindByID() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> playerService.findByID(10L));
    }

    @Test
    void shouldAttackPlayer() {
        Player attacker = playerService.save(new Player("nickname", 100, 10));
        Player defender = playerService.save(new Player("nickname", 100, 10));

        playerService.attackPlayer(attacker.getId(), defender.getId());

        assertThat(playerService.findByID(defender.getId()).get().getHealth()).isEqualTo(90);
    }


}
