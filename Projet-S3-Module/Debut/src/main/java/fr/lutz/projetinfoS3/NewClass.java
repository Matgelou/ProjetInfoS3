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
     

    public static void createTablePerson(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                    """
               create table Person(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 dateNaissance date
               )"""
            );
        }
    }

    public static void createSchema(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            // on veut que le schema soit entierement créé ou pas du tout
            // il nous faut plusieurs ordres pour créer le schema
            // on va donc explicitement gérer les connections
            con.setAutoCommit(false);
            st.executeUpdate(
                    """
               create table Person(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null,
                 dateNaissance date
               )
               """);
            st.executeUpdate(
                    """
               create table Surnom(
                 id integer primary key generated always as identity,
                 nom varchar(50) not null
               )
               """);
            st.executeUpdate(
                    """
               create table PersonSurnoms(
                 idPerson integer,
                 idSurnom integer,
               )
               """);
            st.executeUpdate(
                    """
               alter table PersonSurnoms 
                 add constraint fk_Person_Surnoms_idPerson
                 foreign key(idPerson)
                 references Person(id)
                   on delete restrict
                   on update restrict
               """);
            st.executeUpdate(
                    """
               alter table PersonSurnoms 
                 add constraint fk_Person_Surnoms_idSurnom
                 foreign key(idSurnom)
                 references Surnom(id)
                   on delete restrict
                   on update restrict
               """);
            // si j'arrive ici, c'est que tout s'est bien passé
            // je valide la transaction
            con.commit();
        } catch (SQLException ex) {
            // si quelque chose se passe mal, j'annule la transaction
            // avant de resignaler l'exception
            con.rollback();
            throw ex;
        } finally {
            // pour s'assurer que le autoCommit(true) reste le comportement
            // par défaut (utile dans la plupart des "select"
            con.setAutoCommit(true);
        }
    }

    public static void deleteSchema(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            con.setAutoCommit(false);
            // pour être sûr de pouvoir supprimer les tables,
            // le plus simple est de supprimer d'abord toutes
            // les contraintes
            st.executeUpdate(
               """
               alter table PersonSurnoms 
                 drop constraint fk_Person_Surnoms_idPerson
               """);
            st.executeUpdate(
                    """
               alter table PersonSurnoms 
                 drop constraint fk_Person_Surnoms_idSurnom
               """);
            st.executeUpdate("drop table Person");
            st.executeUpdate("drop table Surnom");
            st.executeUpdate("drop table PersonSurnoms");
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            throw ex;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static void createPerson(Connection con,
            String nom, java.sql.Date dateNaiss) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Person (nom,dateNaissance)
          values (?,?)
        """)) {
            pst.setString(1, nom);
            pst.setDate(2, dateNaiss);
            pst.executeUpdate();
        }
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
"postgres", "postgres", "pass")) {
afficheToutesPersonnes(con); // ici le programme
} catch (ClassNotFoundException | SQLException ex) {
throw new Error(ex);
}
    
}
     }
