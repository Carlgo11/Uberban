package com.carlgo11.uberban;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mysql {

    public static String url = "jdbc:mysql://localhost:3306/ban?zeroDateTimeBehavior=convertToNull";
    public static String username = "Ban";
    public static String password = "H84VAp3BzBXhWxRR";

    public static boolean addBan(String User, String UUID, String Reason, int time, String banner)
    {
        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(Mysql.url, Mysql.username, Mysql.password);
            st = con.createStatement();
            if (!ifPlayerBanned(UUID)) { // Insert new ban
                st.executeQuery("INSERT INTO `ban`.`bans` (`name`, `UUID`, `reason`, `time`, `banner`) VALUES ('" + User + "', '" + UUID + "', '" + Reason + "', '" + time + "', '" + banner + "');");
            } else { // Update existing ban
                st.executeQuery("UPDATE `ban`.`bans` SET `reason` = '" + Reason + "', `time` '" + time + "', `banner` = '" + banner + "' WHERE `bans`.`UUID` = '" + UUID + "';");
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Mysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Mysql.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return true;
    }

    public static boolean ifPlayerBanned(String UUID)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Mysql.url, Mysql.username, Mysql.password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * from bans");
            while (true) {
                if (rs.next()) {
                    System.out.println(rs.getString(2) + "\t" + UUID);
                    if (rs.getString(2).equals(UUID)) {
                        System.out.println("true");
                        return true;
                    }
                } else {
                    System.out.println("false");
                    break;
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Mysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Mysql.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }

}
