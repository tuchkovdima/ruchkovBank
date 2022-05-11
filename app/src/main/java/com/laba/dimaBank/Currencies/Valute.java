package com.laba.dimaBank.Currencies;

public class Valute
{
    private int NumCode;
    private String CharCode;
    private int Nominal;
    private String Name;
    private double Value;

    public int getNumCode()
    {
        return NumCode;
    }

    public void setNumCode(int numCode)
    {
        this.NumCode = numCode;
    }

    public String getCharCode()
    {
        return CharCode;
    }

    public void setCharCode(String charCode)
    {
        this.CharCode = charCode;
    }

    public int getNominal()
    {
        return Nominal;
    }

    public void setNominal(int nominal)
    {
        this.Nominal = nominal;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public double getValue()
    {
        return Value;
    }

    public void setValue(double value)
    {
        this.Value = value;
    }
}
