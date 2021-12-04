/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matlu
 */
public class NewClass {
     public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        try {
                Class.forName("org.postgresql.Driver");
              } catch (ClassNotFoundException ex) {
              throw new Error("Pilote introuvable");
}
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
      public static void afficheToutesPersonnes(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from person");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : " + nom + " né le " + dn);
            }
        }
    }
     public static void main(String[] args) {
try ( Connection con = connectPostgresql("localhost", 5432,
"postgres", "postgres", "mypass")) {
afficheToutesPersonnes(con); // ici le programme
} catch (ClassNotFoundException | SQLException ex) {
throw new Error(ex);
}
    
}
     }
