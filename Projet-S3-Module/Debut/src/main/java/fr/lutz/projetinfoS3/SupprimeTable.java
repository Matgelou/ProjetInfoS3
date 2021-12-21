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
public class SupprimeTable {
            public static void supprimeInscription(Connection con,
            String name ) throws SQLException {  
      try {  
            try (Statement st = con.createStatement()) {
             st.executeUpdate("delete * from inscription");
            }
        } catch (Exception e) {
            con.rollback();
            System.out.println("module n'existe pas");
        }
    }
    
}
