/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
/**getIdUser
 *
 * @author Melliti
 */
public class Commande {

    private int idCommande,idPanier;
    private int idUser;
    private Date dateCommande, dateLivraison;
    private double prixTotal;
    private boolean etatCommande;
    
    
    /***********************/
    
    /*************************/
    public Commande(int idCommande)
    {
        this.idCommande=idCommande;
    }
    /***********************/
    public Commande(int idCommande,int idPanier, int idUser, Date dateCommande, Date dateLivraison, double prixTotal, boolean etatCommande) {
        this.idCommande = idCommande;
        this.idPanier=idPanier;
        this.idUser = idUser;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.prixTotal = prixTotal;
        this.etatCommande = etatCommande;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public int getIdUser() {
        return idUser;
    }

    public boolean isEtatCommande() {
        return etatCommande;
    }
    
    
    /**************************/
    public int getIdCommande() {
        return idCommande;
    }
    
    
    /***********************/
    public int getUserId () {
        return idUser;
    }
    /***********************/
    public Date getDateCommande() {
        return dateCommande;
    }
    /***********************/
    public Date getDateLivraison() {
        return dateLivraison;
    }
    /***********************/
    public double getPrixTotal() {
        return prixTotal;
    }
    /***********************/
    public boolean getEtatCommande() {
        return etatCommande;
    }
    /***********************/
    

}
