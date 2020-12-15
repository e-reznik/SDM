package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

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
            LOGGER.error(ex);
        }

        // Produkte aus der DB mit JPA einlesen
//        products = r.read();
        p.process(products, days);
    }
}
