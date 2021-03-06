package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.Test;
import com.mycompany.sdm.interfaces.Properties;

class ReaderTest {

    /**
     * Test of read method, of class Reader.
     *
     * @throws java.io.FileNotFoundException
     */
    @Test
    void testRead_InputStreamReader() throws FileNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("csv/products.csv");
        InputStreamReader isr = new InputStreamReader(is);
        Reader instance = new Reader();

        List<Product> result = instance.read(isr);
        List<Product> expResult = new ArrayList<>();

        expResult.add(new Product(Properties.ProductTypes.KAESE, "FETA", 14, 55, 917.0, false));
        expResult.add(new Product(Properties.ProductTypes.KAESE, "CAMEMBERT", 33, 71, 390.0, false));
        expResult.add(new Product(Properties.ProductTypes.KAESE, "HARZER", 6, 80, 696.0, false));
        expResult.add(new Product(Properties.ProductTypes.WEIN, "SANGIOVESE", 23, 193, 4590.0, false));
        expResult.add(new Product(Properties.ProductTypes.WEIN, "SANGIOVESE", 42, 250, 3509.0, false));
        expResult.add(new Product(Properties.ProductTypes.AEPFEL, "JONAGOLD", 19, 16, 312.0, false));

        assertIterableEquals(expResult, result);
    }

}
