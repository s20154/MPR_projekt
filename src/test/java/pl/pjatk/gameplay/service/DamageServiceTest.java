package pl.pjatk.gameplay.service;

import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;

import static org.assertj.core.api.Assertions.assertThat;


public class DamageServiceTest {

    private DamageService damageService = new DamageService();

    @Test
    void shouldHealPlayer() {
        //given
        Player player = getPlayer();
        //when
        damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(200);
    }

    @Test
    void shouldHealPlayerd() {
        //given
        Player player = getPlayer();
        //when
        damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(200);
    }

    @Test
    void shouldHealPlayerdd() {
        //given
        Player player = getPlayer();
        //when
        damageService.heal(player);
        //then
        assertThat(player.getHealth()).isEqualTo(200);
    }

    private Player getPlayer() {
        return new Player("my nickname", 100, 10);
    }
}
