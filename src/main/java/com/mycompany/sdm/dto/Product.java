package com.mycompany.sdm.dto;

import com.opencsv.bean.CsvBindByName;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;
import com.mycompany.sdm.interfaces.Properties;

@Entity
public class Product implements Properties, Serializable {

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

    public Product(ProductTypes type, String title, int quality, int bestBefore, double price, boolean disposable) {
        this.type = type;
        this.title = title;
        this.quality = quality;
        this.bestBefore = bestBefore;
        this.price = price;
        this.disposable = disposable;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.quality != other.quality) {
            return false;
        }
        if (this.bestBefore != other.bestBefore) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.disposable != other.disposable) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return this.type == other.type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + this.quality;
        hash = 37 * hash + this.bestBefore;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 37 * hash + (this.disposable ? 1 : 0);
        return hash;
    }

}
