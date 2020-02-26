/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entite;

import java.sql.Date;

/**
 *
 * @author SBS
 */
public class Tab_Demande {

    private String nom;
    private String prenom;
    private int id;
    private String cin;
    private int numtel;
    private String cv;
    private java.sql.Date dateNaissance;
    private String etude;
    private String poste;
    private String etat;

    public Tab_Demande(String nom) {
        this.nom = nom;
    }

    public Tab_Demande() {
    }

    public Tab_Demande(String nom, String prenom, String cin, int numtel, String cv, Date dateNaissance, String etude) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numtel = numtel;
        this.cv = cv;
        this.dateNaissance = dateNaissance;
        this.etude = etude;
    }

    public Tab_Demande(String nom, String prenom, int id, String cin, int numtel, String cv, java.sql.Date dateNaissance, String etude, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.cin = cin;
        this.numtel = numtel;
        this.cv = cv;
        this.dateNaissance = dateNaissance;
        this.etude = etude;
        this.poste = poste;
    }

    public Tab_Demande(String nom, String prenom, String cin, int numtel, String cv, java.sql.Date dateNaissance, String etude, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numtel = numtel;
        this.cv = cv;
        this.dateNaissance = dateNaissance;
        this.etude = etude;
        this.poste = poste;
    }

    public Tab_Demande(String nom, String prenom, int id, String cin, int numtel, String cv, java.sql.Date dateNaissance, String etude, String poste, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
        this.cin = cin;
        this.numtel = numtel;
        this.cv = cv;
        this.dateNaissance = dateNaissance;
        this.etude = etude;
        this.poste = poste;
        this.etat = etat;
    }

    public Tab_Demande(String nom, String prenom, String cin, int numtel, String cv, java.sql.Date dateNaissance, String etude, String poste, java.sql.Date date_inter) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numtel = numtel;
        this.cv = cv;
        this.dateNaissance = dateNaissance;
        this.etude = etude;
        this.poste = poste;
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public int getNumtel() {
        return numtel;
    }

    public String getCv() {
        return cv;
    }

    public java.sql.Date getDateNaissance() {
        return dateNaissance;
    }

    public String getEtude() {
        return etude;
    }

    public String getPoste() {
        return poste;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setDateNaissance(java.sql.Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setEtude(String etude) {
        this.etude = etude;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Tab_Demande{" + "nom=" + nom + ", prenom=" + prenom + ", id=" + id + ", cin=" + cin + ", numtel=" + numtel + ", cv=" + cv + ", dateNaissance=" + dateNaissance + ", etude=" + etude + ", poste=" + poste + ", etat=" + etat + '}';
    }




}
