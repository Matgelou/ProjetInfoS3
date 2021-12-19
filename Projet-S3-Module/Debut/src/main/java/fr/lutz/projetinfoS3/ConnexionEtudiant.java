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
        public static int trouveMdpEtudiant(Connection con, String mdp)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where motDePasse = ?")) {
            pst.setString(1, mdp);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
       public static int mdpEtudiantOk(Connection con, String email, String mdp)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where motDePasse = ? and email= ?")) {
            pst.setString(1, mdp);
            pst.setString(2,email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
    public static String connexionEtudiant (Connection con, String email, String mdp)
        throws SQLException {
        
        int resultat =trouveMdpEtudiant(con,mdp);
        int result = trouveEmailEtudiant(con,email);
        int resulta = mdpEtudiantOk(con,email,mdp);
        if (result==-1) {
            return("email  introuvable");
            
        }
        else if (resultat==-1) {
            return("mot de passe incorect");
            
        }
        else if(resulta==1){
              return ("connexion ok");
        }
        else{
            return("mot de passe ou identifiant incorrect");
        }

      
}
}
