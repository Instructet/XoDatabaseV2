package de.xenso.database;

import de.xenso.XoDatabaseV2;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class XoConListener implements Listener {

    private XoDatabaseV2 mainInst;

    public XoConListener(XoDatabaseV2 mainInst) {
        this.mainInst = mainInst;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        try {
            XoCustomPlayer playerData = new XoCustomPlayer(mainInst, player.getUniqueId());
            mainInst.getXoPlayerManager().addXoCustomPlayer(player.getUniqueId(), playerData);
        } catch (SQLException ex) {
            player.kick(Component.text("Your data could not be loaded!"));
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        mainInst.getXoPlayerManager().removeXoCustomPlayer(e.getPlayer().getUniqueId());
    }
}
