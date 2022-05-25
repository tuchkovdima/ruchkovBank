package com.laba.dimaBank.model;

import java.io.Serializable;

public class GetCurrency implements Serializable
{
    private String currencyName;
    private String description;
    private double buy;
    private double sell;

    public GetCurrency(String currencyName, String description, double buy, double sell)
    {
        this.currencyName = currencyName;
        this.description = description;
        this.buy = buy;
        this.sell = sell;
    }

    public String getCurrencyName()
    {
        return currencyName;
    }

    public void setCurrencyName(String currencyName)
    {
        this.currencyName = currencyName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getBuy()
    {
        return buy;
    }

    public void setBuy(double buyData)
    {
        this.buy = buyData;
    }

    public double getSell()
    {
        return sell;
    }

    public void setSell(double sellData)
    {
        this.sell = sellData;
    }
}
