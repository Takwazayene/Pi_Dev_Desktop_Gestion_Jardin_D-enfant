
package entities;

import java.sql.Date;



public class Event {
    private int idEvent;
  //  private int idClub;
    private String nomEvent;
    private String categorieEvent;
    private int nbrPlaceDispo;
    private Date dateEvent;
    private String description;
    private String nomclub;

    public String getNomclub() {
        return nomclub;
    }

    public void setNomclub(String nomclub) {
        this.nomclub = nomclub;
    }

    public Event(String nomEvent, String categorieEvent, int nbrPlaceDispo, Date dateEvent, String description) {
        
        this.nomEvent = nomEvent;
        this.categorieEvent = categorieEvent;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.dateEvent = dateEvent;
        this.description = description;
    }

    public Event(int idEvent, String nomEvent, String categorieEvent, int nbrPlaceDispo, Date dateEvent, String description) {
        this.idEvent = idEvent;
      //  this.idClub = idClub;
        this.nomEvent = nomEvent;
        this.categorieEvent = categorieEvent;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.dateEvent = dateEvent;
        this.description = description;
    }

    public Event(String nom, String categorie, Date date, String description, int nbrplace, String nomClub) {
 
        this.nomclub = nomClub;
        this.nomEvent = nom;
        this.categorieEvent = categorie;
        this.nbrPlaceDispo = nbrplace;
        this.dateEvent = date;
        this.description = description;  
    }

  
   /* public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }*/

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public void setCategorieEvent(String categorieEvent) {
        this.categorieEvent = categorieEvent;
    }

    public void setNbrPlaceDispo(int nbrPlaceDispo) {
        this.nbrPlaceDispo = nbrPlaceDispo;
    }

 
    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public String getCategorieEvent() {
        return categorieEvent;
    }

    public int getNbrPlaceDispo() {
        return nbrPlaceDispo;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

  

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", nomEvent=" + nomEvent + ", categorieEvent=" + categorieEvent + ", nbrPlaceDispo=" + nbrPlaceDispo + ", dateEvent=" + dateEvent + ", description=" + description + '}';
    }

 

    
}
