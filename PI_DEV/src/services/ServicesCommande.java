/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.esprit.Utils.DataBase;
import entities.Produit;
import entities.Commande;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Melliti
 */
public class ServicesCommande {
    
     private Connection con;
    private Statement ste;

    public ServicesCommande() {
        con = DataBase.getInstance().getConnection();

    }
    
     public void ajouterCommande(Commande p) throws SQLException {

        Statement st;
         st = con.createStatement();
        String req = "insert into Commandes values(" + p.getUserId()+p.getDateCommande()+ "," + p.getDateLivraison()+ "," + p.getPrixTotal()+ "," + p.getEtatCommande()+ ")";
        
        
        st.executeUpdate(req);
    }

    /**
     * ***********************
     */
    public void modifierCommande(Commande p) throws SQLException {
        PreparedStatement pt = con.prepareStatement("update Commandes set idUser = ?,dateCommande = ? , dateLivraison = ? , prixTotal = ? , etatCommande = ? where idPanier = ? ");

        pt.setString(1, p.getUserId());
      
        pt.setString(2, p.getDateCommande());
        pt.setString(3, p.getDateLivraison());
        pt.setDouble(4, p.getPrixTotal());
        pt.setDate(5, p.getEtatCommande());

        pt.setInt(2, p.getIdProduit());

        pt.executeUpdate();

    }

    /**
     * ***********************
     */

    public void afficherCommande() {

        PreparedStatement pt = c.prepareStatement("select * from Commandes");
        ResultSet rs = pt.executeQuery();

        while (rs.next()) {
            System.out.println("Iser ID : " + rs.getString(2) + "Prix Total : " + rs.getInt(5) + "Date Livraison : " + rs.getString(4)+"Date D'ajout : "+rs.getDouble(3)+"Etat Commande :"+rs.getDate(6));
        }

    }
    /**
     * ***********************
     */
    public void supprimerCommande(Produit p)
        {
          PreparedStatement pt = c.prepareStatement("delete from produits where idProduit = ?");
          pt.setInt(1,p.getIdProduit());
        }
    /**
     * ***********************
     */

    
}
