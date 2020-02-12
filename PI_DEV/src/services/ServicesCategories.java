/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Melliti
 */
public class ServicesCategories {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import entities.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



    
    
     public void ajouterCategories(Categorie p) throws SQLException {

        Statement st = c.createStatement();
        String req = "insert into Categories values("+p.getLabel()+")";
        ); 
        
        st.executeUpdate(req);
    }

    /**
     * ***********************
     */
    public void modifierCategorie(Categorie p) throws SQLException {
        PreparedStatement pt = c.prepareStatement("update Categorie set label = ? where id = ?");

        pt.setString(1, p.getLabel());
 
        pt.setInt(2, p.getIdCategorie());

        pt.executeUpdate();

    }

    /**
     * ***********************
     */

    public void afficherCategorie() throws SQLException {

        PreparedStatement pt = c.prepareStatement("select * from categories");
        ResultSet rs = pt.executeQuery();

        while (rs.next()) {
            System.out.println("Label : " + rs.getString(2));
        }

    }
    /**
     * ***********************
     */
    public void supprimerCatgeories(Categorie p)
        {
          PreparedStatement pt = c.prepareStatement("delete from categories where idCategorie = ?");
          pt.setInt(1,p.getIdProduit());
        }
    /**
     * ***********************
     */

    
}

    
