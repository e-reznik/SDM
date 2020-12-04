package com.mycompany.sdm.dto;

import com.mycompany.sdm.interfaces.IProperties;
import com.opencsv.bean.CsvBindByName;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Product implements IProperties, Serializable {

    @CsvBindByName
    @Enumerated(EnumType.STRING)
    private ProductTypes type;
    @CsvBindByName
    private String title;
    @CsvBindByName
    private int quality;
    @CsvBindByName
    private int bestBefore;
    @CsvBindByName
    private double price;
    @Transient
    private boolean disposable;
    @Id
    private Long id;

    public Product() {
    }

    public ProductTypes getType() {
        return type;
    }

    public void setType(ProductTypes type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(int bestBefore) {
        this.bestBefore = bestBefore;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDisposable() {
        return disposable;
    }

    public void setDisposable(boolean disposable) {
        this.disposable = disposable;
    }

    @Override
    public String toString() {
        return "Product{" + "type=" + type + ", title=" + title + ", quality=" + quality + ", bestBefore=" + bestBefore + ", price=" + price + ", disposable=" + disposable + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
