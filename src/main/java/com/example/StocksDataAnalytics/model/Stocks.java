package com.example.StocksDataAnalytics.model;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.print.attribute.standard.DateTimeAtCreation;

@Entity
@Table(name = "stocks")
public class Stocks {

    private long id;
    private String name;
    private double currentPrice;
    private String currencyId;
    private DateTimeAtCompleted lastUpdate;
    private Boolean isActive;
    private long quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private long userId;

    public Stocks() {

    }

    public Stocks(String name, double currentPrice, String currencyId, DateTimeAtCompleted lastUpdate,
                  Boolean isActive, long quantity, long userId) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.currencyId = currencyId;
        this.lastUpdate = lastUpdate;
        this.isActive = isActive;
        this.quantity = quantity;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "current_price", nullable = false)
    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Column(name = "currency_id", nullable = false)
    public String getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    @Column(name = "last_update", nullable = false)
    public DateTimeAtCompleted getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(DateTimeAtCompleted lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(name = "is_active", nullable = false)
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }

    @Column(name = "quantity", nullable = false)
    public long getQuantity() {
        return quantity;
    }
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Stocks [id=" + id + ", name=" + name + ", currentPrice=" + currentPrice
                + ", currencyId=" + currencyId + ", lastUpdate=" + lastUpdate
                + ", isActive=" + isActive + ", quantity=" + quantity + ", userId=" + userId
                + "]";
    }
}
