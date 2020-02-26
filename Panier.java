/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.LinkedList;

/**
 *
 * @author Melliti
 */
public class Panier {

    private int idPanier, idUser;
    private double prixTotal;
    LinkedList<LigneCommande> EnsembleDesLignesDesCommandes;

    /**
     * **************************
     */
    public Panier(int idPanier, int idUser) {
        this.idPanier = idPanier;
        this.idUser = idUser;
        EnsembleDesLignesDesCommandes = new LinkedList<>();

    }

    /**
     * ***********************
     */
    public int getIdUser() {
        return idUser;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    /**
     * ***********************
     */
    public void addArticle(LigneCommande p) {
        //EnsembleDesLignesDesCommandes.add(p);
        EnsembleDesLignesDesCommandes.push(p);
    }

    /**
     * ********************************
     */
    public boolean DeleteArticle(int index) {
        EnsembleDesLignesDesCommandes.remove(index);
        return true;

    }

    /**
     * ****************************
     */
    public int getIdPanier() {
        return idPanier;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public LinkedList<LigneCommande> getEnsembleDesLignesDesCommandes() {
        return EnsembleDesLignesDesCommandes;
    }

}
