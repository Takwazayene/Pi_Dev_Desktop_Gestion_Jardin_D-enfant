/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DemandeInscri {
    Date dateCourante = new Date();
    DateFormat formatJJMMAAAA = new SimpleDateFormat("dd/MM/yyyy");
    String a=formatJJMMAAAA.format(dateCourante);
    public int IdDI;
    public String nomP;
    public String prenomP;
    public int Num ;
    public String adres;
    public String Email;
    public String nomE;
    public String prenomE;
    public int ageE;
    public String cin;
    public String date;
    public int ETATt;
    public int Etatp;

    public DemandeInscri(int IdDI, String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE, String cin, String date, int ETATt, int Etatp) {
        this.IdDI = IdDI;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
        this.cin = cin;
        this.date = date;
        this.ETATt = ETATt;
        this.Etatp = Etatp;
    }
    
    public DemandeInscri() {
    }

    public DemandeInscri(int IdDI, String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE) {
        
        this.IdDI = IdDI;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
        
    }

  /*  public DemandeInscri(String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE,String date) {
        Date dateCourante = new Date();
        DateFormat formatJJMMAAAA = new SimpleDateFormat("dd/MM/yyyy");
        String a=formatJJMMAAAA.format(dateCourante);
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
        this.date = a;
    }*/

    public DemandeInscri(int IdDI, String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE, String cin) {
        this.IdDI = IdDI;
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
        this.cin = cin;
    }

    public DemandeInscri(String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE, String cin) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
        this.cin = cin;
        
    }
    

    public DemandeInscri(String nomP, String prenomP, int Num, String adres, String Email, String nomE, String prenomE, int ageE) {
        this.nomP = nomP;
        this.prenomP = prenomP;
        this.Num = Num;
        this.adres = adres;
        this.Email = Email;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.ageE = ageE;
    }

   
    public int getIdDI() {
        return IdDI;
    }

    public String getNomP() {
        return nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public int getNum() {
        return Num;
    }

    public String getAdres() {
        return adres;
    }

    public String getEmail() {
        return Email;
    }

    public String getNomE() {
        return nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public int getAgeE() {
        return ageE;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public void setAgeE(int ageE) {
        this.ageE = ageE;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getETATt() {
        return ETATt;
    }

    public void setETATt(int ETATt) {
        this.ETATt = ETATt;
    }

    public int getEtatp() {
        return Etatp;
    }

    public void setEtatp(int Etatp) {
        this.Etatp = Etatp;
    }

    @Override
    public String toString() {
        return "DemandeInscri{" + "IdDI=" + IdDI + ", nomP=" + nomP + ", prenomP=" + prenomP + ", Num=" + Num + ", adres=" + adres + ", Email=" + Email + ", nomE=" + nomE + ", prenomE=" + prenomE + ", ageE=" + ageE + '}';
    }
    
    
}
