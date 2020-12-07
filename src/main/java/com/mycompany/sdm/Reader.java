package com.mycompany.sdm;

import com.mycompany.sdm.interfaces.IProperties;
import com.mycompany.sdm.dto.Product;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Reader implements IProperties {

    /**
     * Liest Produkte aus einer CSV ein.
     *
     * @param isr
     * @return Liste der eingelesenen Produktei
     * @throws FileNotFoundException
     */
    public List<Product> read(InputStreamReader isr) throws FileNotFoundException {
        return fromCsv(isr);
    }

    /**
     * Liest Produkte aus der DB mit JPA ein.
     *
     * @return Liste der eingelesenen Produkte
     */
    public List<Product> read() {
        return fromDb();
    }

    private List<Product> fromDb() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit1");
        EntityManager em = factory.createEntityManager();

        TypedQuery<Product> query
                = em.createQuery("SELECT c FROM Product c", Product.class);
        return query.getResultList();
    }

    /**
     * Deserialisiert eine CSV zum Product POJO.
     *
     * @param path Pfad zur CSV
     * @return Liste mit Products
     * @throws FileNotFoundException
     */
    private List<Product> fromCsv(InputStreamReader isr) {
        return new CsvToBeanBuilder<Product>(isr)
                .withType(Product.class)
                .build().parse();
    }
}
