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
public class SupprimeLigne {
          public static void supprimeGroupeModule(Connection con,
            Integer n ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("delete from groupeModule where creneau= " + n);
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("creneau inexistant");
        }
    }
              public static void supprimeSemestre(Connection con,
            Integer n , Integer m) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("delete from semestre where numero= " + m+"and annee="+n);
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("semestre inexistant");
        }
    }
                public static void supprimeModule(Connection con,
            String name ) throws SQLException {  
      try {  
            try (Statement st = con.createStatement()) {
             st.executeUpdate("delete from module where nom ='"+name+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("module n'existe pas");
        }
    }
                  public static void supprimeEtudiant(Connection con,
            String name ) throws SQLException {
      try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("delete from etudiant where nom= '"+name+"';");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("creneau inexistant");
        }
    }
    
}
