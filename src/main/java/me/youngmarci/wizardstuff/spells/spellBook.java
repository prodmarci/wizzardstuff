package me.youngmarci.wizardstuff.spells;

import me.youngmarci.wizardstuff.Wizardstuff;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.HashMap;
import java.util.Map;
public class spellBook implements Listener {

    Wizardstuff wizzStuff;
    public Map<String, String> freezeEnemySpellEq;
    public Map<String, String> freezeEnemySpellUUID;

    public Map<String, String> teleportSpellEq;
    public Map<String, String> teleportSpellUUID;

    public Map<String, String> decaySpellEq;
    public Map<String, String> decaySpellUUID;

    public Map<String, String> manaStealSpellEq;
    public Map<String, String> manaStealSpellUUID;

    public Map<String, String> switchPlaceSpellEq;
    public Map<String, String> switchPlaceSpellUUID;

    public Map<String, String> meteorRainSpellEq;
    public Map<String, String> meteorRainSpellUUID;

    public Map<String, String> circleOfFireSpellEq;
    public Map<String, String> circleOfFireSpellUUID;

    public HashMap<String, Integer> manaCount;

    public spellBook(Wizardstuff w) { // (Require instance of Wizardstuff) Accessing variables from main class (Wizardstuff)
        this.wizzStuff = w;

        this.freezeEnemySpellEq = w.freezeEnemySpellEq;
        this.freezeEnemySpellUUID = w.freezeEnemySpellUUID;

        this.teleportSpellEq = w.teleportSpellEq;
        this.teleportSpellUUID = w.teleportSpellUUID;

        this.decaySpellEq = w.decaySpellEq;
        this.decaySpellUUID = w.decaySpellUUID;

        this.manaStealSpellEq = w.manaStealSpellEq;
        this.manaStealSpellUUID = w.manaStealSpellUUID;

        this.switchPlaceSpellEq = w.switchPlaceSpellEq;
        this.switchPlaceSpellUUID = w.switchPlaceSpellUUID;

        this.meteorRainSpellEq = w.meteorRainSpellEq;
        this.meteorRainSpellUUID = w.meteorRainSpellUUID;

        this.circleOfFireSpellEq = w.circleOfFireSpellEq;
        this.circleOfFireSpellUUID = w.circleOfFireSpellUUID;

        this.manaCount = w.manaCount;
    }

    @EventHandler
    public void spellWriting(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        BookMeta bookmeta = event.getNewBookMeta();

        if (!freezeEnemySpellEq.containsKey(playerUUID) && !teleportSpellEq.containsKey(playerUUID) && !decaySpellEq.containsKey(playerUUID) && !manaStealSpellEq.containsKey(playerUUID) && !switchPlaceSpellEq.containsKey(playerUUID) && !meteorRainSpellEq.containsKey(playerUUID) && !circleOfFireSpellEq.containsKey(playerUUID)) { // If any spell is not equipped
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("SpellBook")) { // Check for SpellBook in main hand
                String pageContent = bookmeta.getPage(1);

                if (pageContent.contains("ice")) { // Check if content in book matches spell name
                    wizzStuff.addFreezeEnemySpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped freeze enemy spell!");
                } else if (pageContent.contains("tp")) {
                    wizzStuff.addTeleportSpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped teleportation spell!");
                } else if (pageContent.contains("dcy")) {
                    wizzStuff.addDecaySpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped decay spell!");
                } else if (pageContent.contains("mstl")) {
                    wizzStuff.addManaStealSpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped mana steal spell!");
                } else if (pageContent.contains("switch")) {
                    wizzStuff.addSwitchPlaceSpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped place switch spell!");
                } else if (pageContent.contains("mtrn")) {
                    wizzStuff.addMeteorRainSpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped meteor rain spell!");
                } else if (pageContent.contains("cfr")) {
                    wizzStuff.addCircleOfFireSpellEq(playerUUID, playerName);

                    player.sendMessage("You equipped circle of fire spell!");
                }
            }
        }
    }
}
