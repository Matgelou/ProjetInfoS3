/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lutz.projetinfoS3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author matlu
 */
public class CreationLigne {
    public static void createModuleOuvert(Connection con,
           Integer idsemestre, Integer idmodule) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into moduleouvert (idsemestre,idmodule)
          values (?,?)
        """)) {
            pst.setInt(1, idsemestre);
            pst.setInt(2, idmodule);
           
            pst.executeUpdate();
        }
    }
    public static void createEtudiant(Connection con,
            String nom, String prenom, java.sql.Date datedenaissance,  String email,String motdepasse) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into etudiant (nom,prenom,datedenaissance,motdepasse,email)
          values (?,?,?,?,?)
        """)) {
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setDate(3, datedenaissance);
            pst.setString(4, motdepasse);
            pst.setString(5,email);
            pst.executeUpdate();
        }
    }

      public static void createModule(Connection con,
            String nom, Integer nbrePersonneMax, Integer nbrePersonneMin) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into module (nom,nbrePersonneMax,nbrePersonneMin)
          values (?,?,?)
        """)) {
            pst.setString(1, nom);
            pst.setInt(2,nbrePersonneMax );
            pst.setInt(3, nbrePersonneMin);
           
            pst.executeUpdate();
        }
    }
  public static void createAdmin(Connection con,
            String nom, String prenom,  String motdepasse, String email) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into administrateur (nom,prenom,motdepasse,email)
          values (?,?,?,?)
        """)) {
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, motdepasse);
            pst.setString(4,email);
            pst.executeUpdate();
        }
    }
  public static void createGroupeModule(Connection con,
            Integer creneau ) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into groupeModule (creneau)
          values (?)
        """)) {
            pst.setInt(1,creneau);
            
            pst.executeUpdate();
        }
    }

  public static void createSemestre(Connection con,
           Integer annee, Integer numero) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into semestre (annee, numero)
          values (?,?)
        """)) {
            pst.setInt(1, annee);
            pst.setInt(2, numero);
           
            pst.executeUpdate();
        }
    }
  public static void createInscription(Connection con,
           Integer idetudiant, Integer idmodule) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into inscription (idetudiant,idmodule)
          values (?,?)
        """)) {
            pst.setInt(1, idetudiant);
            pst.setInt(2, idmodule);
           
            pst.executeUpdate();
        }
    }
   public static void createHistorique(Connection con,
           Integer idetudiant, Integer idmodule) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into inscription (idetudiant,idmodule)
          values (?,?)
        """)) {
            pst.setInt(1, idetudiant);
            pst.setInt(2, idmodule);
           
            pst.executeUpdate();
        }
    }

  public static void createCreneau(Connection con,
           Integer idgroupeModule, Integer idmodule) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into creneau (idgroupemodule,idmodule)
          values (?,?)
        """)) {
            pst.setInt(1, idgroupeModule);
            pst.setInt(2, idmodule);
           
            pst.executeUpdate();
        }
    }
    
}
