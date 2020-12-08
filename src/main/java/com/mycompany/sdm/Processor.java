package com.mycompany.sdm;

import com.mycompany.sdm.interfaces.IProperties;
import com.mycompany.sdm.dto.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processor implements IProperties {

    private static final Logger LOGGER = Logger.getLogger(Processor.class.getName());

    public Processor() {
    }

    /**
     * Verarbeitung der Produkte nach festgelegten Regeln.
     *
     * @param products Liste mit den Produkten
     * @param days Anzahl Tage (Iterationen)
     * @return aktualisierte Liste mit den Produkten
     */
    public List<Product> process(List<Product> products, int days) {
        processProducts(products, days);
        return products;
    }

    /**
     * Vorverarbeitung: Entferne Produkte mit minderer Qualität (Qualität <
     * vorderfinierter Wert). Kann ausgeführt werden, wenn neue Produkte ins
     * Regal einsortiert werden sollen.
     *
     * Käse < 30 Wein < 1 (1 ist default) Äpfel < 10
     *
     * @param products Vorverarbeitete Liste ohne abgelaufene Produkte
     */
    private List<Product> preprocessing(List<Product> products) {
        for (IProperties.ProductTypes pt : IProperties.ProductTypes.values()) {
            products.removeIf(
                    o -> o.getType() == pt
                    && o.getQuality() < qualities.get(pt).getMinQuality()
            );
        }
        return products;
    }

    /**
     * Regeln anwenden und Produkte ausgeben.
     *
     * @param products Liste mit den Produkten
     */
    private void processProducts(List<Product> products, int days) {
        for (int i = 1; i <= days; i++) {
            LOGGER.log(Level.INFO, "Tag: {0}", i);

            for (Product p : products) {
                applyRules(p, i);
            }

            LOGGER.log(Level.INFO, "\n---\n");
            removeExpiredProducts(products);
//            removeDisposableProducts(products);
        }
    }

    /**
     * Verarbeitungsregeln pro Produkt anwenden.
     *
     * @param p das zu verarbeitende Produkt
     * @param i der aktuelle Tag
     */
    private void applyRules(Product p, int i) {
        /* Produktinfos */
        IProperties.ProductTypes type = p.getType();
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

        LOGGER.log(Level.INFO, p.toString());

        // täglich das Verfallsdatum um 1 Tag verringern
        p.setBestBefore(bestBefore - 1);
    }

    /**
     * Abgelaufene Produkte entfernen.
     *
     * @param products aktualisierte Liste
     */
    private void removeExpiredProducts(List<Product> products) {
        products.removeIf(o -> o.getBestBefore() < 1);
    }

    /**
     * Produkte, die entsorgt werden können (niedrige Qualität), entfernen.
     *
     * @param products aktualisierte Liste
     */
    private void removeDisposableProducts(List<Product> products) {
        products.removeIf(o -> o.isDisposable());
    }

}
