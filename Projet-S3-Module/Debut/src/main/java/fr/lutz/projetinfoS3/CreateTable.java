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
public class CreateTable {
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

public static void createTableModuleOuvert(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                  """
               create table moduleouvert(
                 id integer primary key generated always as identity,
                   idsemestre INTEGER  ,
                   idmodule INTEGER,  
                  FOREIGN KEY (idsemestre) REFERENCES semestre(id))
               """
            );
        }
    }
 public static void createTableInscription(Connection con) throws SQLException {
        try ( Statement st = con.createStatement()) {
            st.executeUpdate(
                  """
               create table inscription(                
                    etudiant INTEGER NOT NULL,
                   module INTEGER NOT NULL)
               """
            );
        }
    }
    
}
