package de.xenso.database;

import com.zaxxer.hikari.HikariDataSource;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XoDatabase {

    //User is just an example which isn't existing in our live project, this is for public upload purposes
    public final String user = "MusterUser";
    private final String pw = "MusterPassword";
    private HikariDataSource hikari;
    public void setConnectionPool() {
        hikari = new HikariDataSource();
        hikari.setJdbcUrl("jdbc:mysql://localhost:3306/test_schema");
        hikari.addDataSourceProperty("user", user);
        hikari.addDataSourceProperty("password", pw);
        hikari.setMaximumPoolSize(5);
    }

    public void closeConnectionPool() {
        if (isConnected()) {
            hikari.close();
        }
    }

    public boolean isConnected() { return hikari != null; }
    public HikariDataSource getHikari() { return hikari; }

    //Returns a Connection of the Hikari Connection-Pool, return null if failed
    public Connection getCon() {
        try {
            return getHikari().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            broadcastSQLMessage("getCon() didnt work!");
        }
        return null;
    }

    //Shows the SQL messages ingame only for the users, which have the permission "xenso.xosql"
    public void broadcastSQLMessage(String message) {
        Bukkit.broadcast(Component.text(message), "xenso.xosql");
    }

    //Custom SQL-Update, as boolean to prevent data loss on failed updates
    public boolean update(String sqlCommand) {
        if (sqlCommand == null) {
            return false;
        } else {
            boolean result = false;
            try {
                if (isConnected()) {
                    PreparedStatement ps = getHikari().getConnection().prepareStatement(sqlCommand);
                    ps.executeUpdate();
                    ps.close();
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                broadcastSQLMessage("Failed update SQL-Command: " + sqlCommand);
            }
            return result;
        }
    }

    //Custom query, returns the requested data otherwise null, please use with auto-closable (try with resources)
    public ResultSet query(String sqlCommand) throws SQLException {
        if (sqlCommand == null) {
            return null;
        } else {
                if (isConnected()) {
                    ResultSet rs;
                    PreparedStatement ps = getHikari().getConnection().prepareStatement(sqlCommand);
                    rs = ps.executeQuery(sqlCommand);
                    return rs;
                }
        }
        return null;
    }
}
