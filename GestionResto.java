/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionresto;

import java.sql.SQLException;
import java.util.List;
import entities.Resto;
import services.ServiceResto;
import IServices.IService;
import utils.DataBase;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class GestionResto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
             ServiceResto ser = new ServiceResto();
        
        Resto r1 = new Resto(1,"resto1", 21, 10,true);
        Resto r2 = new Resto(2,"resto2", 300, 10,false);
        
        try {
//         
            ser.ajouter(r1);
            ser.ajouter(r2);
          // List<Resto> list = ser.readAll();
           //System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
