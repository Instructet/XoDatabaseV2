package de.xenso.database;

import java.util.HashMap;
import java.util.UUID;

public class XoPlayerManager {

    private HashMap<UUID, XoCustomPlayer> xoplayer = new HashMap<>();

    public XoCustomPlayer getXoPlayer(UUID uuid) {
        return xoplayer.get(uuid);
    }

    public void addXoCustomPlayer(UUID uuid, XoCustomPlayer xoCustomPlayer) {
        xoplayer.put(uuid, xoCustomPlayer);
    }

    public void removeXoCustomPlayer(UUID uuid) {
        xoplayer.remove(uuid);
    }

}
