/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.HashMap;

/**
 *
 * @author Melliti
 */
public class Panier {
    private int idPanier ;
    private double prixTotal;
    private HashMap<Integer,Produit> LigneCommandes;

    public Panier(int idPanier, double prixTotal) {
        this.idPanier = idPanier;
        this.prixTotal = prixTotal;
    }

    
    
    public boolean addArticle(Produit p)
    {
      LigneCommandes.put(LigneCommandes.size()+1, p);
    
    }
    /*******************************/
    public int getIdPanier() {
        return idPanier;
    }

    public double getPrixTotal() {
        return prixTotal;
    }
    
    
}
