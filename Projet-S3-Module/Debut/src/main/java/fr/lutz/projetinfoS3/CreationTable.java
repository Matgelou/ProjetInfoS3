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
import java.time.format.DateTimeParseException;
import java.util.Random;

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
                    "select * from person");
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
  public static void tabledrop(Connection con, String nomtable) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table " + nomtable);
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("table inexistante");
        }
    }
      public static LocalDate entreeDate(LocalDate defaut) {
        LocalDate res = null;
        while (res == null) {
            String date = Console.entreeString(("entrez une date au format aaaa-mm-jj : (ou rien pour defaut = " + defaut + " : "));
            if (date.trim().length() == 0) {
                res = defaut;
            } else {
                try {
                    res = LocalDate.parse(date);
                } catch (DateTimeParseException ex) {
                    System.out.println("format invalide");
                }
            }
        }
        return res;
    }

public static void menuText(Connection con) {
        int rep = -1;
        Random r = new Random();
        while (rep != 0) {
            try {
                System.out.println("Voulez-vous : ");
                System.out.println("1) créer le schema");
                System.out.println("2) supprimer tout");
                System.out.println("3) créer des personnes");
                System.out.println("4) créer des modules");
                System.out.println("5) créer des semestres");
                System.out.println("6) créer des liens ouvert (module,semestre)");
                System.out.println("7) créer des liens inscription (personne,module,semestre)");
                System.out.println("8) recherche (select) sql quelconque");
                System.out.println("9) afficher des tables");
                System.out.println("10) créer les données de la correction du TD2");
                System.out.println("0) Quitter");
                rep = fr.lutz.projetinfoS3.Console.entreeInt("votre choix : ");
                
              if (rep == 3) {
                    int nbr = Console.entreeEntier("combien de personnes : ");
                    System.out.println("date de naissance minimale : ");
                    LocalDate dmin = entreeDate(LocalDate.parse("1960-01-01"));
                    System.out.println("date de naissance maximale : ");
                    LocalDate dmax = entreeDate(LocalDate.parse("2005-01-01"));
                    createPersonnesAlea(con, nbr, dmin, dmax, r);
                } else if (rep == 4) {
                    int nbr = ConsoleFdB.entreeEntier("combien de modules : ");
                    int min = ConsoleFdB.entreeEntier("nbr places min : ");
                    int max = ConsoleFdB.entreeEntier("nbr places max : ");
                    double p = ConsoleFdB.entreeDouble("proba qu'un module possède un responsable : ");
                    createModulesAlea(con, nbr, min, max, p, r);
                } else if (rep == 5) {
                    int amin = ConsoleFdB.entreeEntier("année min : ");
                    int amax = ConsoleFdB.entreeEntier("année max : ");
                    createSemestresAlea(con, amin, amax, r);
                } else if (rep == 6) {
                    int nbr = ConsoleFdB.entreeEntier("combien d'ouvertures : ");
                    createOuverturesAlea(con, nbr, r);
                } else if (rep == 7) {
                    int nbr = ConsoleFdB.entreeEntier("combien d'inscriptions : ");
                    createInscriptionsAlea(con, nbr, r);
                } else if (rep == 8) {
                    String sql = ConsoleFdB.entreeString("entrez un select : ");
                    afficheSQLQuery(con, sql);
                } else if (rep == 9) {
                    menuAffTables(con);
                } else if (rep == 10) {
                    createExempleTD2(con);
                } else if (rep != 0) {
                    System.out.println("choix invalide : " + rep);
                }
            } catch (SQLException ex) {
                System.out.println("Erreur : " + ex.getLocalizedMessage());

            }
        }
    }
    public static void main(String[] args) {
        try ( Connection con = connectPostgresql(
                "localhost", 5432,
                "postgres", "postgres", "pass")) {
            System.out.println("Connexion OK");
            tabledrop(con,"etudiant");
            tabledrop(con,"administrateur");
            tabledrop(con,"module");
            tabledrop(con,"groupeModule");
            tabledrop(con,"semestre");
            
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
            createSemestre(con,1,3);
            afficheEtudiant(con);
            afficheSemestre(con);
            afficheModule(con);
            
        } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
    }
}

    

