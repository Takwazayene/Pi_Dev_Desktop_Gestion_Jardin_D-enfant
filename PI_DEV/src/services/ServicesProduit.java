/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Iservices.IService;
import com.esprit.Utils.DataBase;
import entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melliti
 */
public class ServicesProduit  implements IService<Produit>{
    
    private Connection con;
    private Statement ste;

    public ServicesProduit() {
        con = DataBase.getInstance().getConnection();

    }

    /**
     * ***********************
     * @param p
     */
    @Override
    public void ajouter(Produit p) throws SQLException {

        Statement st;
        st = con.createStatement();
        
        String req ;
        req= "insert into Produits(quantite,libelle,Description,img,prix,date_ajout,idCategorie) values(" + p.getQuantite()+","+ p.getLibelle() + "," + p.getDesc() + "," + p.getImg() + "," + p.getPrix() + "," + p.getDate_ajout()+"," +p.getIdCategorie()+ ")"; 
        
        st.executeUpdate(req);
    }

    /**
     * ***********************
     */
    @Override
    public void update(Produit p) throws SQLException {
        PreparedStatement pt = c.prepareStatement("update Produits set libelle = ?,quantite = ? , description = ? , img = ? , prix = ? , date_ajout = ? ,idCategorie = ? where id = ?");

        pt.setString(1, p.getLibelle());
        pt.setInt(2, p.getQuantite());
        pt.setString(3, p.getDesc());
        pt.setString(4, p.getImg());
        pt.setDouble(5, p.getPrix());
        pt.setDate(6, (Date) p.getDate_ajout());
        pt.setInt(7, p.getIdCategorie());

        pt.setInt(8, p.getIdProduit());

        pt.executeUpdate();

    }

    /**
     * ***********************
     */

    public void afficherProduit() {

        PreparedStatement pt = c.prepareStatement("select * from produits");
        ResultSet rs = pt.executeQuery();

        while (rs.next()) {
            System.out.println("Libelle : " + rs.getString(3) + "Quantité Disponible : " + rs.getInt(2) + "Description : " + rs.getString(4)+"Prix : "+rs.getDouble(5)+"Date Ajout :"+rs.getDate(6)+"Categorie : "+);
        }

    }
    /**
     * ***********************
     */
    @Override
    public void delete(Produit p)
        {
          PreparedStatement pt = con.prepareStatement("delete from produits where idProduit = ?");
          pt.setInt(1,p.getIdProduit());
        }

    @Override
    public List<Produit> readAll() throws SQLException {
        List<Produit> arr=new ArrayList<>();
        System.out.print("txt");
        return arr;
    }
    /**
     * ***********************
     */

}
