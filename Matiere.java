/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Entite;

/**
 *
 * @author ines
 */
public class Matiere {

    private int id;
    private String nom;
    private int coeff;
    private int nbh;
    private String enseignant;

    public Matiere(int id, String nom, int coeff, int nbh, String enseignant) {
        this.id = id;
        this.nom = nom;
        this.coeff = coeff;
        this.nbh = nbh;
        this.enseignant = enseignant;
    }
    public Matiere( String nom, int coeff, int nbh, String enseignant) {
        this.nom = nom;
        this.coeff = coeff;
        this.nbh = nbh;
        this.enseignant = enseignant;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getCoeff() {
        return coeff;
    }

    public int getNbh() {
        return nbh;
    }


        public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public void setNbh(int nbh) {
        this.nbh = nbh;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    @Override
    public String toString() {
        return "Matiere{" + "id=" + id + ", nom=" + nom + ", coeff=" + coeff + ", nbh=" + nbh + ", enseignant=" + enseignant + '}';
    }


    
}
