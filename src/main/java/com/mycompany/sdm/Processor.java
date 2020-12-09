package com.mycompany.sdm;

import com.mycompany.sdm.dto.Product;
import java.util.List;
import com.mycompany.sdm.interfaces.Properties;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Processor implements Properties {

    private static final Logger LOGGER = LogManager.getLogger(Processor.class);

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
        for (Properties.ProductTypes pt : Properties.ProductTypes.values()) {
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
            LOGGER.info("Tag: " + i);

            for (Product p : products) {
                applyRules(p, i);
            }

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
        Properties.ProductTypes type = p.getType();
        int quality = p.getQuality();
        int bestBefore = p.getBestBefore();
        double price = p.getPrice();

        /* Qualitätsinfos */
        int minQuality = qualities.get(type).getMinQuality();
        int qualityChange = qualities.get(type).getQualityChange();
        int changeAfterDays = qualities.get(type).getChangeQualAfterDays();

        /* Ändere die Qualität nach einer bestimmten Anzahl an Tagen */
        if (i % changeAfterDays == 0) {
            p.setQuality(quality + qualityChange);
        }

        /* Wenn die Qualität ein bestimmtes Niveau unterschreitet 
        oder wenn das MHD erreicht ist, wird das Produkt entsorgt.
        Gilt nicht für Wein. */
        if (!type.equals(type.WEIN)) {
            if (!type.equals(type.WEIN)
                    && (quality < minQuality || bestBefore < 1)) {
                p.setDisposable(true);
            }

            // Tagespreis anpassen
            price = price + (0.1 * quality);

            // täglich das Verfallsdatum um 1 Tag verringern
            p.setBestBefore(bestBefore - 1);
        }

        LOGGER.log(Level.INFO, p.toString());
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
