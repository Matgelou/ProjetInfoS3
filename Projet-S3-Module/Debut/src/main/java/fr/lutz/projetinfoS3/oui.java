/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lutz.projetinfoS3;

  import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author matlu
 */
public class oui {
 
     public static void testConnect() {
        Connection con = null;
        try {
            // teste la présence du driver postgresql
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");

            con.setAutoCommit(false);
            Statement st = con.createStatement();
            st.executeUpdate(
                    """
                create table testConnect (
                  id integer primary key generated always as identity,
                  nom varchar(50)
                )
                """);
            st.executeUpdate(
                 """
                create table yoo (
                  id integer primary key generated always as identity,
                  nom varchar(50)
                )
                """);
        
            PreparedStatement pst = con.prepareStatement(
                    """
                insert into testConnect (nom) values (?)
                """);

            for (String nom : new String[]{"toto", "titi"}) {
                pst.setString(1, nom);
                pst.executeUpdate();
            }

            System.out.println("table testConnect : ");
            ResultSet retrouve = st.executeQuery("select * from testConnect");
            while (retrouve.next()) {
                System.out.println(
                        retrouve.getInt("id") + " : "
                        + retrouve.getString("nom"));
            }
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {}
            }
            throw new Error(ex);
        }

    }
    
    public static void main(String[] args) {
        testConnect();
        System.out.println("salut a tous");
    }
}
