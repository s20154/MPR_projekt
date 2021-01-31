package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private DamageService damageService;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void findAll() {
        //Given
        when(playerRepository.findAll())
                .thenReturn(List.of(new Player()));
        //When
        List<Player> all = playerService.findAll();
        //Then
        assertThat(all).hasSize(1);
    }


    @Test
    void deleteById() {
        //Given

        //When
        playerService.deleteById(1L);
        playerService.deleteById(2L);
        playerService.deleteById(2L);
        //Then
        verify(playerRepository, times(3)).deleteById(anyLong());
//        verify(playerRepository, times(1)).deleteById(2L);
    }

    @Test
    void save(){
        //given
        Player player = new Player("Kanapka", 100, 100);
        when(playerRepository.save(player)).thenReturn(new Player(50L, "Kanapka", 100, 100));
        //when
        Player save = playerService.save(player);
        //then
        assertThat(save.getId()).isEqualTo(50L);
    }

    @Test
    void shouldAttackPlayer() {
        Player player1 = new Player(1L, "test 1", 1000, 50);
        Player player2 = new Player(2L, "test 2", 1000, 50);

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player1));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(player2));
        when(playerRepository.save(player2)).thenReturn(player2);
        when(damageService.attackPlayer(any(), any())).thenCallRealMethod();

        Player attack1 = playerService.attackPlayer(1L, 2L);
        assertThat(attack1.getHealth()).isEqualTo(950);
    }
}