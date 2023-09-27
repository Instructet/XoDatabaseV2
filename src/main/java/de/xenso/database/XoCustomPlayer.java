package de.xenso.database;

import de.xenso.XoDatabaseV2;

import java.sql.*;
import java.util.UUID;

public class XoCustomPlayer {

    private XoDatabaseV2 mainInst;
    private UUID uuid;
    private String rank;
    private int cookies;
    private int kills;

    public XoCustomPlayer(XoDatabaseV2 mainInst, UUID uuid) throws SQLException {
        this.mainInst = mainInst;
        this.uuid = uuid;

        PreparedStatement ps = mainInst.getXodb().getCon().prepareStatement("SELECT * FROM players_test WHERE UUID = '" + uuid.toString() + "';");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //if exists -> loading
            rank = rs.getString("SRANK");
            kills = rs.getInt("KILLS");
            cookies = rs.getInt("COOKIES");
        } else {
            //if not default settings ->
            rank = "GUEST";
            kills = 0;
            cookies = 0;
            mainInst.getXodb().update("INSERT INTO players_test (ID, UUID, SRANK, KILLS, COOKIES) VALUES (" +
                    "default, " +
                    "'" + uuid + "'," +
                    "'" + rank + "'," +
                    "'" + kills + "'," +
                    "'" + cookies + "');");
        }
    }

    public void setRank(String rank) {
        this.rank = rank;
        try {
            mainInst.getXodb().update("UPDATE players_test SET SRANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCookies(int cookies) {
        this.cookies = cookies;
        try {
            mainInst.getXodb().update("UPDATE players_test SET COOKIES = '" + cookies + "' WHERE UUID = '" + uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKills(int kills) {
        this.kills = kills;
        try {
            mainInst.getXodb().update("UPDATE players_test SET KILLS = '" + kills + "' WHERE UUID = '" + uuid + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRank() {
        return rank;
    }
    public int getCookies() {
        return cookies;
    }
    public int getKills() {
        return kills;
    }

}
