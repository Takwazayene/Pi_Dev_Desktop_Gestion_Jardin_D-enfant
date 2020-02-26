/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class Enfant {
    public int id ;
    public int IdParent ;
    public String nomE;
    public String prenomE;
    public int age ;
    public int IdGroupe ;

    public Enfant() {
    }

    public Enfant(int IdParent, String nomE, String prenomE, int age, int IdGroupe) {
        this.IdParent = IdParent;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.age = age;
        this.IdGroupe = IdGroupe;
    }
    
    
    public Enfant(int id, int IdParent, String nomE, String prenomE, int age, int IdGroupe) {
        this.id = id;
        this.IdParent = IdParent;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.age = age;
        this.IdGroupe = IdGroupe;
    }

    public int getId() {
        return id;
    }

    

    public int getIdParent() {
        return IdParent;
    }

    public void setIdParent(int IdParent) {
        this.IdParent = IdParent;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdGroupe() {
        return IdGroupe;
    }

    public void setIdGroupe(int IdGroupe) {
        this.IdGroupe = IdGroupe;
    }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", IdParent=" + IdParent + ", nomE=" + nomE + ", prenomE=" + prenomE + ", age=" + age + ", IdGroupe=" + IdGroupe + '}';
    }
    
   

}
