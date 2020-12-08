package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Dao {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit1");
    EntityManager em = factory.createEntityManager();

    /**
     * Lädt alle Produkte aus der DB.
     *
     * @return Liste mit den Produkten
     */
    public List<Product> readAllProducts() {
        TypedQuery<Product> query = em.createQuery("SELECT c FROM Product c", Product.class);
        return query.getResultList();

    }

}
