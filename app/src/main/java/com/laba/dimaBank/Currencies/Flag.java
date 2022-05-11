package com.laba.dimaBank.Currencies;

import android.content.Context;
import android.widget.ImageView;

public class Flag
{
    private String CurrencyName;
    private String CountryName;

    public String getCurrencyName()
    {
        return CurrencyName;
    }

    public String getCountryName()
    {
        return CountryName;
    }

    public Flag (String CurrencyName, String CountryName)
    {
        this.CurrencyName = CurrencyName;
        this.CountryName = CountryName;
    }

    // Установка картинки в указанный ImageView из текущего объекта Flag
    public void setImageFromFlag(Context context, ImageView imageView)
    {
        String dir = "com.laba.dimaBank:drawable/";
        try
        {
            int imgId = context.getResources().getIdentifier(dir + CountryName, null, null);
            imageView.setImageResource(imgId);
        }
        catch (Exception e)
        {
            int imgId = context.getResources().getIdentifier(dir + "ru", null, null);
            imageView.setImageResource(imgId);
        }
    }
}
