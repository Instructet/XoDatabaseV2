package de.xenso.database;

import java.util.HashMap;
import java.util.UUID;

//Reference from UUID to the Xenso player
public class XoPlayerManager {

    private HashMap<UUID, XoCustomPlayer> xoplayer = new HashMap<>();

    public void addXoCustomPlayer(UUID uuid, XoCustomPlayer xoCustomPlayer) {
        xoplayer.put(uuid, xoCustomPlayer);
    }

    public void removeXoCustomPlayer(UUID uuid) {
        xoplayer.remove(uuid);
    }

}
