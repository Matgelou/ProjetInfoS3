/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lutz.projetinfoS3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author matlu
 */
public class AfficheBdd {
      public static void afficheEtudiant(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from etudiant");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
               
                System.out.println(id + " : " + nom );
            }
        }
    }
      public static void afficheSemestre(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from semestre");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                Integer annee = res.getInt("annee");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
               
                System.out.println(id + "année du semestre" + annee);
            }
        }
    }
        public static void afficheModule(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from module");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                
                System.out.println(id + " : " + nom );
            }
        }
    }
          public static void afficheGroupeModule(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from groupeModule");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                Integer creneau = res.getInt("creneau");
              
                System.out.println(id + " : " + creneau );
            }
        }
    }
            public static void afficheAdmin(Connection con)
            throws SQLException {
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery(
                    "select * from administrateur");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
               
                System.out.println(id + " : " + nom );
            }
        }
    }
}
