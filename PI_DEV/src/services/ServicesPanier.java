/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Iservices.IService;
import com.esprit.Utils.DataBase;
import entities.Produit;
import entities.Panier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Melliti
 */
public class ServicesPanier  implements IService<Produit>{
    
    private Connection con;
    private Statement ste;

    public ServicesPanier() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Panier p) throws SQLException {
        Statement st;
        st = con.createStatement();
        String req = "insert into Paniers values(" + p.getIdPanier()+","+p.getPrixTotal()+")"; 
        
        st.executeUpdate(req);
    }

    @Override
    public boolean delete(Produit p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Panier p) throws SQLException {
         PreparedStatement pt = c.prepareStatement("update Paniers set prixTotal = ?, where idPanier = ?");

        
        
        pt.setDouble(1, p.getPrixTotal());

        pt.setInt(2, p.getIdPanier());

        pt.executeUpdate();
    }

    @Override
    public List<Produit> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
