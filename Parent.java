
package entities;


public class Parent {
    public int id;
    public String nom;
    public String prenom;
    public int num;
    public String adres;
    public String mail;

    public Parent() {
    }

    public Parent(int id, String nom, String prenom, int num, String adres, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.adres = adres;
        this.mail = mail;
    }

    public Parent(String nom, String prenom, int num, String adres, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.adres = adres;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    

    
}
