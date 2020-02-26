/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Melliti
 */
public class LigneCommande {
    private int    idLigneCommandes , idCommande,idProduit ,quantite;
    private String nomProduit;
    /*********************************/
    public LigneCommande(int idLigneCommandes)
    {
        this.idLigneCommandes = idLigneCommandes;
    }
    /*********************************/
    public void setNomProduit(String nomProduit) {    
        this.nomProduit = nomProduit;
    }

    /*********************************/
    public void setIdProduit(int idProduit) {    
        this.idProduit = idProduit;
    }

    /*********************************/
    public LigneCommande(int idLigneCommandes, int idCommande, int idProduit, int quantite) {
        this.idLigneCommandes = idLigneCommandes;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }
    /*********************************/
    public int getIdLigneCommandes() {
        return idLigneCommandes;
    }
    /*********************************/
    public int getIdPanier() {
        return idCommande;
    }
    /*********************************/
    public int getIdProduit() {
        return idProduit;
    }
    /*********************************/
    public int getQuantite() {
        return quantite;
    }
    /*********************************/
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProduit() {
        return nomProduit;
    }
    
    
    
    
    /*********************************/
    
    
    

    
}
