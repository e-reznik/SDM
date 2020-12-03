package com.mycompany.sdm.dto;

import com.mycompany.sdm.interfaces.IProperties;

public class Properties implements IProperties {

    private int minQuality;
    private int qualityChange;
    private int changeQualAfterDays;

    public Properties(int minQuality, int qualityChange, int changeQualAfterDays) {
        this.minQuality = minQuality;
        this.qualityChange = qualityChange;
        this.changeQualAfterDays = changeQualAfterDays;
    }

    public int getChangeQualAfterDays() {
        return changeQualAfterDays;
    }

    public void setChangeQualAfterDays(int changeQualAfterDays) {
        this.changeQualAfterDays = changeQualAfterDays;
    }

    public int getMinQuality() {
        return minQuality;
    }

    public void setMinQuality(int minQuality) {
        this.minQuality = minQuality;
    }

    public int getQualityChange() {
        return qualityChange;
    }

    public void setQualityChange(int qualityChange) {
        this.qualityChange = qualityChange;
    }

}
