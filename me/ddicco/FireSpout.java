package me.ddicco.firespout;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.FireAbility;


public class FireSpout extends FireAbility implements AddonAbility {
	private Location location;
	private long time;
	
    public FireSpout(Player player) {
		super(player);
		if (!bPlayer.canBend(getAbility("FireJet"))) {
			return;
		}
		
		time = System.currentTimeMillis();
		bPlayer.addCooldown(getAbility("FireJet"), getCooldown());
		setFields();
		
		start();
		
	}
	
	public void setFields() {
		player.getAllowFlight();
		player.isFlying();
		location = player.getLocation();
		player.setAllowFlight(true);
		player.setFlying(true);
	}
	
	@Override
	public long getCooldown() {
		// TODO Auto-generated method stub
		return 5000;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return player.getLocation();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FireSpout";
	}
	
	@Override
	public boolean isHiddenAbility() {
		return true;
	}

	@Override
	public boolean isHarmlessAbility() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void progress() {
		if(player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		location = player.getLocation();
		player.setAllowFlight(true);
		player.setFlying(true);
		playFirebendingParticles(location, 1, 0.2F, 0.2F, 0.2F);
		if(System.currentTimeMillis() > time + 5000) {
			player.setAllowFlight(false);
			player.setFlying(false);
			remove();
			return;
		}
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "ddicco";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.5";
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		 ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new FireSpoutListener(), ProjectKorra.plugin);
		 
		 ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor());

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		 ProjectKorra.log.info("Successfully disabled " + getName() + " by " + getAuthor());
		 
		 super.remove();
	}

}
