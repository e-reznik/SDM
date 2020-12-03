package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import com.mycompany.sdm.interfaces.IProperties;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements IProperties {

    private static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    /**
     *
     * @param path
     * @throws FileNotFoundException
     */
    public void init(String path) throws FileNotFoundException {
        List<Product> products = read(path);

        processProducts(preprocessing(products), 20);
    }

    /**
     * Deserialisiert eine CSV zum Product POJO.
     *
     * @param path Pfad zur CSV
     * @return Liste mit Products
     * @throws FileNotFoundException
     */
    private List<Product> read(String path) throws FileNotFoundException {
        return new CsvToBeanBuilder<Product>(new FileReader(path))
                .withType(Product.class).build().parse();
    }

    /**
     * Vorverarbeitung: Entferne Produkte mit minderer Qualität (Qualität <
     * vorderfinierter Wert). Wird ausgeführt, wenn neue Produkte ins Regal
     * einsortiert werden sollen.
     *
     * Käse < 30
     * Wein < 1 (1 ist default)
     * Äpfel < 10
     *
     * @param products Vorverarbeitete Liste ohne "schlechte" Produkte
     */
    private List<Product> preprocessing(List<Product> products) {
        for (ProductTypes pt : ProductTypes.values()) {
            products.removeIf(o -> o.getType() == pt
                    && o.getQuality() < qualities.get(pt).getMinQuality());
        }
        return products;
    }

    /**
     * Regeln anwenden und Produkte ausgeben.
     *
     * @param products Liste mit den Produkten
     */
    private void processProducts(List<Product> products, int days) {
        for (int i = 0; i <= days; i++) {
            LOGGER.log(Level.INFO, "Tag: {0}", 1);

            for (Product p : products) {
                applyRules(p, i);
            }

//            LOGGER.log(Level.INFO, "---");
            removeExpiredProducts(products);
//            removeDisposableProducts(products);
        }
    }

    /**
     * Verarbeitungsregeln anwenden.
     *
     * @param p
     * @param i
     */
    private void applyRules(Product p, int i) {
        /* Produktinfos */
        ProductTypes type = p.getType();
        int quality = p.getQuality();
        int bestBefore = p.getBestBefore();
        double price = p.getPrice();

        /* Qualitätsinfos */
        int minQuality = qualities.get(type).getMinQuality();
        int qualityChange = qualities.get(type).getQualityChange();
        int changeAfterDays = qualities.get(type).getChangeQualAfterDays();

        /* Generische Verarbeitung */
        if (i % changeAfterDays == 0) {
            p.setQuality(quality + qualityChange);

            if (quality < minQuality) {
                p.setDisposable(true);
            }
        }

//        LOGGER.log(Level.INFO, p.toString());
        // täglich das Verfallsdatum um 1 Tag verringern
        p.setBestBefore(bestBefore - 1);
    }

    /**
     * Abgelaufene Produkte entfernen.
     *
     * @param products
     */
    private void removeExpiredProducts(List<Product> products) {
        products.removeIf(o -> o.getBestBefore() < 1);
    }

    /**
     * Produkte, die entsorgt werden können (niedrige Qualität), entfernen.
     *
     * @param products
     */
    private void removeDisposableProducts(List<Product> products) {
        products.removeIf(o -> o.isDisposable());
    }
}
