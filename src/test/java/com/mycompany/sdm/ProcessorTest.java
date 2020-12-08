package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import com.mycompany.sdm.interfaces.IProperties;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    public ProcessorTest() {
    }

    /**
     * Test of process method, of class Processor.
     */
    @Test
    public void testProcess() {
        int days = 30;
        Processor instance = new Processor();
        Reader reader = new Reader();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("csv/products2.csv");
        InputStreamReader isr = new InputStreamReader(is);

        List<Product> result = instance.process(reader.read(isr), days);
        List<Product> expResult = new ArrayList<>();

        expResult.add(new Product(IProperties.ProductTypes.KAESE, "FETA", -16, 25, 917.0, true));
        expResult.add(new Product(IProperties.ProductTypes.KAESE, "CAMEMBERT", 3, 41, 390.0, true));
        expResult.add(new Product(IProperties.ProductTypes.KAESE, "HARZER", -24, 50, 696.0, true));
        expResult.add(new Product(IProperties.ProductTypes.WEIN, "SANGIOVESE", 26, 163, 4590.0, false));
        expResult.add(new Product(IProperties.ProductTypes.WEIN, "SANGIOVESE", 45, 220, 3509.0, false));

        assertIterableEquals(expResult, result);
    }
}
