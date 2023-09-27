package de.xenso;

import de.xenso.database.XoConListener;
import de.xenso.database.XoDatabase;
import de.xenso.database.XoPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class XoDatabaseV2 extends JavaPlugin {

    private XoDatabase xodb;
    private XoPlayerManager xoPlayerManager;

    @Override
    public void onEnable() {
        xodb = new XoDatabase();
        xodb.setConnectionPool();

        xoPlayerManager = new XoPlayerManager();

        Bukkit.getPluginManager().registerEvents(new XoConListener(this), this);
    }

    @Override
    public void onDisable() {
        xodb.closeConnectionPool();
    }

    public XoDatabase getXodb() {
        return xodb;
    }

    public XoPlayerManager getXoPlayerManager() { return xoPlayerManager; }
}
