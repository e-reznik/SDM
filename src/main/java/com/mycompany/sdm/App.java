package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        Reader r = new Reader();
        Processor p = new Processor();

        List<Product> products = null;

        
        // Produkte aus CSV einlesen
//        try {
//            products = r.read("/home/evgenij/products.csv");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }

        // Produkte aus der DB mit JPA einlesen
        products = r.read();
        p.process(products, 30);
    }
}
