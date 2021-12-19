/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lutz.projetinfoS3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matlu
 */
public class ChangeBdd {
               public static void changeNomEtudiant(Connection con,
            String nouveaunom, String nomachange ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("update etudiant set nom='"+nouveaunom+"' where nom='"+nomachange+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("nom  inexistant");
        }
    }
        public static void changeNomModule(Connection con,
            String nouveaunom, String nomachange ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("update module set nom='"+nouveaunom+"' where nom='"+nomachange+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("nom  inexistant");
        }
    }
           public static void changeNombrePlaceMin(Connection con,
            int nouveaunbre, String nommodule ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("update module set nbrePersonneMin="+nouveaunbre+" where nom='"+nommodule+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("nom  inexistant");
        }
    }
              public static void changeNombrePlaceMax(Connection con,
            int nbrenouveau, String nommodule ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("update module set nbrePersonneMAX="+nbrenouveau+" where nom='"+nommodule+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("nom inexistant");
        }
    }
      public static void changeMDP(Connection con,
            String nouveaumdp, String ancienmdp ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("update etudiant set motDePasse='"+nouveaumdp+"' where motDePasse='"+ancienmdp+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("ancien mot de passe faux");
        }
    }
}
