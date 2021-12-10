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
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author matlu
 */
public class CreationTable {
   


    public static Connection connectPostgresql(String host, int port,
            String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://" + host + ":" + port + "/" + database, user, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
//...


 public static void createTableEtudiant(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                       """
               create table etudiant(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                prenom varchar(50) not null,
                 datedenaissance  date ,
                motDePasse varchar(50) not null,
               email varchar(50) not null)
                
               """
            );
        }
    } public static void createTableModule(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                        """
               create table module(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 nbrePersonneMax INTEGER not null,
                nbrePersonneMin INTEGER not null    
            )
               """
                
            );
        }
    } public static void createTableAdmin(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                  """
               create table administrateur(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 prenom varchar(50) not null,
                 motDePasse varchar(50) not null,
                 email varchar(50) not null
               )
               """
            );
        }
    } public static void createTableGroupeModule(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                   """
               create table groupeModule(
                 id integer primary key generated always as identity,
                 creneau  INTEGER NOT NULL
                     
            )
               """
            );
        }
    } public static void createTableSemestre(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                  """
               create table semestre(
                 id integer primary key generated always as identity,
                   annee INTEGER NOT NULL,
                   numero INTEGER NOT NULL)
               """
            );
        }
    }


 
    

    public static void createEtudiant(Connection con,
            String nom, String prenom, java.sql.Date datedenaissance, String motdepasse, String email) throws SQLException {
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


    public static void main(String[] args) {
        try ( Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
            System.out.println("Connexion OK");
            
         createTableEtudiant(con);
         createTableModule(con);
         createTableSemestre(con);
         createTableGroupeModule(con);
         createTableAdmin(con);
            LocalDate ld = LocalDate.of(1985, Month.MARCH, 23);
            java.sql.Date sqld = java.sql.Date.valueOf(ld);
            createEtudiant(con, "Toto","Titi", sqld,"mat","mat");
            createModule(con,"toto",1,1);
            createAdmin(con,"toto","titi","salut","matt");
            createSemestre(con,1,2);
            createGroupeModule(con,1);
            
            
        } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
    }
}

    
