package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import com.mycompany.sdm.interfaces.IProperties;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Reader implements IProperties {

    /**
     * Liest Produkte aus einer CSV ein.
     *
     * @param path Path der CSV
     * @return Liste der eingelesenen Produktei
     * @throws FileNotFoundException
     */
    public List<Product> read(String path) throws FileNotFoundException {
        return fromCsv(path);
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
    private List<Product> fromCsv(String path) throws FileNotFoundException {
        return new CsvToBeanBuilder<Product>(new FileReader(path))
                .withType(Product.class).build().parse();
    }
}
