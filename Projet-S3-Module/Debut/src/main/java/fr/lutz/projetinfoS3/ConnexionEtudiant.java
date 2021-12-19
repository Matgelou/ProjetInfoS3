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
public class ConnexionEtudiant {
    
      public static int trouveEmailEtudiant(Connection con, String email)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where email = ?")) {
            pst.setString(1, email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
        public static int trouveMdpEtudiant(Connection con, String email)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where motDePasse = ?")) {
            pst.setString(1, email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    public static boolean connexionEtudiant (Connection con, String email, String mdp)
        throws SQLException {
        int validation =0;
        trouveEmailEtudiant(con,email);
        trouveEmailEtudiant.getInt= validation;
        if (=1) {
            
        }

        return (true);
}
}
