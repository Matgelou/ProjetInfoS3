/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lutz.projetinfoS3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author matlu
 */
public class ConnexionAdmin {
      public static int trouveEmailAdmin(Connection con, String email)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from administrateur where email = ?")) {
            pst.setString(1, email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
        
    
}
       public static int trouveMdpAdmin(Connection con, String email)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from administrateur where motDePasse = ?")) {
            pst.setString(1, email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
}