package pi_dev;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.*;
import java.sql.SQLData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import services.*;

public class PI_DEV {

    public static void main(String[] args) {
        ServicesProduit ser = new ServicesProduit();

     
	//Date  = new Date(2020, 10, 1) ; 
	String str = "2020-02-02";
        Date date_ajout = Date.valueOf(str);

        System.out.print(date_ajout);

        Produit p;
        p = new Produit(1, 2, 1, "Livres", "Conseillé", "path", 11.75, date_ajout);

        try {

            ser.ajouter(p);

            ser.afficherProduit();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
