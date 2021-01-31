package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;

@Service
public class DamageService {

    public Player attackPlayer(Player attacker, Player defender) {
        defender.setHealth(defender.getHealth() - attacker.getAttack());
        return defender;
    }

    public Player heal(Player player) {
        if (player.getHealth() > 100) {
            player.setHealth(player.getHealth() + 10);
        } else {
            player.setHealth(player.getHealth() + 100);
        }
        return player;
    }

    public Player attackBonus(Player player) {
        if (player.getAttack() >= 30) {
            player.setAttack(player.getAttack() + 5);
        } else {
            player.setAttack(player.getAttack() + 7);
        }
        return player;
    }

    public Player coinToss(Player player) {
        if (player.getHealth() >= 100) {
            player.setHealth(player.getHealth() - 100);
        } else {
            player.setHealth(player.getHealth() + 100);
        }
        return player;
    }

    public Player stupidName(Player player) {
        if (player.getAttack() > 50) {
            player.setNickname("Koks");
        } else {
            player.setNickname("Cienias");
        }
        return player;
    }
}
