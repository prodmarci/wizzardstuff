package me.youngmarci.wizardstuff.spells.spellClasses;

import me.youngmarci.wizardstuff.Wizardstuff;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class riftSpell implements Listener {

    Wizardstuff wizzStuff;
    public Map<String, String> riftSpellUUID;

    public riftSpell(Wizardstuff w) { // (Require instance of Wizardstuff) Accessing variables from main class (Wizardstuff)
        this.wizzStuff = w;
        this.riftSpellUUID = w.riftSpellUUID;
    }

    @EventHandler
    public void projectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Snowball) {
            String snowballUUID = projectile.getUniqueId().toString(); //get the UUID of the snowball

            if (riftSpellUUID.containsKey(snowballUUID)) {
                if (event.getHitBlock() == null) {
                    Location Loc = event.getHitEntity().getLocation();

                    new BukkitRunnable() {
                        final ArrayList<Location> locations = getCircle(Loc.add(0,0.5,0), 2, 25);
                        Integer w = 1;
                        public void run() {
                            if (w == 10) {
                                this.cancel();
                            }
                            w++;
                            for(int i = 0; locations.size() > i; i++) {
                                Location loc2 = locations.get(i);
                                Loc.getWorld().spawnParticle(Particle.FLAME, loc2, 0, 0 ,0 ,0);
                            }
                        }
                    }.runTaskTimer(wizzStuff, 0, 20);

                    //Spawn Creatures

                } else {
                    Location Loc = event.getHitBlock().getLocation();

                    new BukkitRunnable() {
                        final ArrayList<Location> locations = getCircle(Loc.add(0,0.5,0), 2, 25);
                        Integer w = 1;
                        public void run() {
                            if (w == 10) {
                                this.cancel();
                            }
                            w++;
                            for(int i = 0; locations.size() > i; i++) {
                                Location loc2 = locations.get(i);
                                Loc.getWorld().spawnParticle(Particle.FLAME, loc2, 0, 0 ,0 ,0);
                            }
                        }
                    }.runTaskTimer(wizzStuff, 0, 20);

                    //Spawn Creatures

                }
            }
        }
    }

    public ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i = 0;i < amount; i++)
        {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
}
