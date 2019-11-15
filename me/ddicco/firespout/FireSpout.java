package me.ddicco.firespout;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.FireAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class FireSpout extends FireAbility implements AddonAbility {
	
	private long duration;
	private long cooldown;
	private long currenttime;
	
	public FireSpout(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
		
		if(bPlayer.isOnCooldown("FireJet"));
		
		bPlayer.addCooldown("FireJet", cooldown);
		
		setFields();
		start();
	}

	@Override
	public long getCooldown() {
		// TODO Auto-generated method stub
		return 10000;
	}
	
	public void setFields() {
		duration = 5000;
		currenttime = System.currentTimeMillis();
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
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
		if(!bPlayer.canBend(this)) {
			remove();
			return;
		}
		
		if(player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		player.setAllowFlight(true);
		player.setFlying(true);
		
		ParticleEffect.FLAME.display(getLocation(), 0F, 0F, 0F, 0.2F, 2);
		
		if(System.currentTimeMillis() > currenttime + duration) {
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
		return "0.0.1";
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor() + " Version " + getVersion());
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new FireSpoutListener(), ProjectKorra.plugin);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
