package com.mycompany.sdm.interfaces;

import com.mycompany.sdm.dto.Properties;
import java.util.Map;

public interface IProperties {

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
    public Map<ProductTypes, Properties> qualities = Map.of(
            ProductTypes.KAESE, new Properties(30, -1, 1),
            ProductTypes.WEIN, new Properties(1, 1, 10),
            ProductTypes.AEPFEL, new Properties(10, -1, 2)
    );

}
