package com.laba.dimaBank.BankPlaces;

import java.io.Serializable;

public class BankPlace implements Serializable
{
    private String address;
    private String type;
    private String startTime;
    private String closeTime;

    public BankPlace(String address, String type, String startTime, String closeTime)
    {
        this.address = address;
        this.type = type;
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public String getBankPlaceAddress()
    {
        return address;
    }

    public void setBankPlaceAddress(String address)
    {
        this.address = address;
    }

    public String getBankPlaceType()
    {
        return type;
    }

    public void setBankPlaceType(String type)
    {
        this.type = type;
    }

    public String getBankPlaceStartTime()
    {
        return startTime;
    }

    public void setBankPlaceStartTime(String buyData)
    {
        this.startTime = startTime;
    }

    public String getBankPlaceCloseTime()
    {
        return closeTime;
    }

    public void setBankPlaceCloseTime(String sellData)
    {
        this.closeTime = closeTime;
    }
}
