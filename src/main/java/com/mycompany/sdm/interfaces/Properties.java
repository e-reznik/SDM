package com.mycompany.sdm.interfaces;

import com.mycompany.sdm.dto.ProductProperties;
import java.util.Map;

public interface Properties {

    /* Produkttypen */
    public enum ProductTypes {
        KAESE,
        WEIN,
        AEPFEL,
        REST
    }

    /**
     * Welche Qualitäten dürfen jeweils nicht unterschritten werden?
     *
     * Käse: minimale Qualiät: 30; Qualität ändert sich um -1; alle 1 Tage;
     * Wein: minimale Qualiät: 1; Qualität ändert sich um +1; alle 1 Tage;
     * Äpfel: minimale Qualiät: 10; Qualität ändert sich um -1; alle 2 Tage;
     *
     */
    public Map<ProductTypes, ProductProperties> qualities = Map.of(
            ProductTypes.KAESE, new ProductProperties(30, -1, 1),
            ProductTypes.WEIN, new ProductProperties(1, 1, 10),
            ProductTypes.AEPFEL, new ProductProperties(10, -1, 2)
    );

}
