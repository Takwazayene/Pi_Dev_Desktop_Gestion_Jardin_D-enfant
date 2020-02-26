 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class clubs {
    private int id,effectif;
    private String nom,activite;
      
    public clubs(String nom, String activite,int effectif) {
        
        this.nom = nom;
        this.activite = activite;
        
        this.effectif=effectif;
        
    }
    public clubs(){}

    public clubs(int id, int effectif, String nom, String activite) {
        this.id = id;
        this.effectif = effectif;
        this.nom = nom;
        this.activite = activite;
    }

   public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getActivite() {
        return activite;
    }
    
    public int getEffectif ()
    {
            return effectif;
                    
     }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
public void setActivite(String activite) {
        this.activite = activite;
    }

    
    
      
    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    @Override
    public String toString() {
        return "club{" + "id= " + id + ", nom= " + nom + ", activite= " + activite + ", effectif= " + effectif + '}';
    }
    
    
}
