package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.util.List;
import com.mycompany.sdm.interfaces.Properties;

public class Reader implements Properties {

    /**
     * Liest Produkte aus einer CSV ein.
     *
     * @param isr InputStreamReader
     * @return Liste der eingelesenen Produkte
     */
    public List<Product> read(InputStreamReader isr) {
        return fromCsv(isr);
    }

    /**
     * Liest Produkte aus der DB mit JPA ein.
     *
     * @return Liste der eingelesenen Produkte
     */
    public List<Product> read() {
        Dao dao = new Dao();
        return dao.readAllProducts();
    }

    /**
     * Deserialisiert eine CSV zum Product POJO.
     *
     * @param path Pfad zur CSV
     * @return Liste mit Products
     */
    private List<Product> fromCsv(InputStreamReader isr) {
        return new CsvToBeanBuilder<Product>(isr)
                .withType(Product.class)
                .build().parse();
    }
}
