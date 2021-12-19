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
        public static int trouveMdpAdmin(Connection con, String mdp)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from administrateur where motDePasse = ?")) {
            pst.setString(1, mdp);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
       public static int mdpAdminOk(Connection con, String email, String mdp)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from administrateur where motDePasse = ? and email= ?")) {
            pst.setString(1, mdp);
            pst.setString(2,email);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
       
    public static String connexionAdmin (Connection con, String email, String mdp)
        throws SQLException {
        
        int resultat =trouveMdpAdmin(con,mdp);
        int result = trouveEmailAdmin(con,email);
        int resulta = mdpAdminOk(con,email,mdp);
        if (result==-1) {
            return("email  introuvable");
            
        }
        else if (resultat==-1) {
            return("mot de passe incorect");
            
        }
        else if(resulta==-1){
              return ("mot de passe ou identifiant incorrect");
        }
        else{
            return("ok");
        }

      
}
}