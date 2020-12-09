package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        Reader r = new Reader();
        Processor p = new Processor();
        int days = 30;

        List<Product> products = null;

//         Produkte aus CSV einlesen
        try {
            InputStream is = new FileInputStream("/home/evgenij/products.csv");
            InputStreamReader isr = new InputStreamReader(is);

            products = r.read(isr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Produkte aus der DB mit JPA einlesen
//        products = r.read();
        p.process(products, days);
    }
}
