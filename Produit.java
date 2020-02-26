/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

import java.sql.Date;

/**
 *
 * @author Melliti
 */
public class Produit {
    private int idProduit,quantite,idCategorie;
    private String libelle,desc,img;
    private double prix;
    private Date date_ajout;
    
    /************************/
    public Produit(int id) {
        this.idProduit=id;
    }

    /************************/
    public Produit(int idProduit, int quantite, String libelle, String desc, String img, double prix, Date date_ajout, int idCategorie) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.idCategorie = idCategorie;
        this.libelle = libelle;
        this.desc = desc;
        this.img = img;
        this.prix = prix;
        this.date_ajout = date_ajout;
        this.idCategorie=idCategorie;
    }
    /************************/
    public int getIdProduit() {
        return idProduit;
    }
    /************************/
    public int getIdCategorie() {
        return idCategorie;
    }
    /************************/

    public int getQuantite() {
        return quantite;
    }
    /************************/

    public String getLibelle() {
        return libelle;
    }
    /************************/

    public String getDesc() {
        return desc;
    }
    /************************/

    public String getImg() {
        return img;
    }
   /************************/

    public double getPrix() {
        return prix;
    }
    /************************/
    public Date getDate_ajout() {
        return date_ajout;
    }
    
}
