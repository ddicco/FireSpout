package me.ddicco.firespout;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.projectkorra.projectkorra.BendingPlayer;

import me.ddicco.firespout.FireSpout;

public class FireSpoutListener implements Listener {
	@EventHandler
	public void PlayerSneakAnimationEvent(PlayerToggleSneakEvent event) {
		
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		
		if (event.isCancelled() || bPlayer == null) {
			return;
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("FireJet")) {
			new FireSpout(player);
			
		}
	}

}
