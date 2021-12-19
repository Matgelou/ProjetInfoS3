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
    
 public static void createOuvert(Connection con,
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
            public static int trouveEtudiant(Connection con, String nom)
            throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                "select id from etudiant where nom = ?")) {
            pst.setString(1, nom);
            ResultSet findP = pst.executeQuery();
            if (!findP.next()) {
                return -1;
            }
            return findP.getInt(1);
        }
    }
       
            

  public static void tabledrop(Connection con, String nomtable) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try { 
            con.setAutoCommit(false);
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table " + nomtable);
            }
        } catch (Exception e) {
           
            con.rollback();
            System.out.println("table inexistante");
        }
    }
   public static boolean inscriptionExists(Connection con, int etudiantId, int moduleId, int semestreId) throws SQLException {
        try (Statement st = con.createStatement();
                ResultSet test = st.executeQuery("select * from inscription where "
                        + "etudiant = " + etudiantId
                        + " and module = " + moduleId
                        + " and semestre = " + semestreId)) {
            return test.next();
        }
    }
   
   public static final String[][] foreignKeys = new String[][]{
      
        {"Ouvert", "module", "Module", "id"},
        {"Ouvert", "semestre", "Semestre", "id"},
        {"Inscription", "etudiant", "Etudiant", "id"},
        {"Inscription", "module", "Module", "id"},
        {"Inscription", "semestre", "Semestre", "id"}
    };

 
    
   
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
            createSemestre(con,2,1);
            createGroupeModule(con,1);
             createGroupeModule(con,1);
              createGroupeModule(con,2);
            createSemestre(con,1,3);
            createSemestre(con,8,3);
            afficheEtudiant(con);
            afficheSemestre(con);
            createTableModuleOuvert(con);
            afficheModule(con);
            createOuvert(con,1,8);
            createEtudiant(con,"matthieu","lut",sqld,"mqqqqq","mooo");
            changeNomEtudiant(con,"benjamin","matthieu");
            changeNombrePlaceMax(con,50,"toto");
            
            
           
            
        } catch (Exception ex) {
            System.out.println("Probleme : " + ex);
        }
    }
}

    

