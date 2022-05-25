package com.laba.dimaBank.model;

import android.app.Application;
import com.laba.dimaBank.model.BankPlace;
import com.laba.dimaBank.model.GetCurrency;
import com.laba.dimaBank.controller.FlagPicture;
import java.util.ArrayList;
import java.util.List;

public class PictureNamePlus extends Application
{

    private final List<BankPlace> bankPlaces = new ArrayList<>();
    private final List<GetCurrency> currencies = new ArrayList<>();
    private final List<FlagPicture>flagList=new ArrayList<>();

    public PictureNamePlus()
    {
        super();
        flagsInit();        // Инициализация флагов - составление сопоставлений (Название валюты <=> Название страны)
    }

    // Инициализация флагов - составление сопоставлений (Название валюты <=> Название страны)
    private void flagsInit()
    {

        flagList.add(new FlagPicture("AUD","ai"));
        flagList.add(new FlagPicture("GBP","uk"));
        flagList.add(new FlagPicture("BYR","by"));
        flagList.add(new FlagPicture("HKD","hk"));
        flagList.add(new FlagPicture("USD","us"));
        flagList.add(new FlagPicture("EUR","eu"));
        flagList.add(new FlagPicture("RUB","ru"));
        flagList.add(new FlagPicture("AZN","az"));
        flagList.add(new FlagPicture("AMD","am"));
        flagList.add(new FlagPicture("BYN","by"));
        flagList.add(new FlagPicture("BGN","bg"));
        flagList.add(new FlagPicture("BRL","br"));
        flagList.add(new FlagPicture("HUF","hu"));
        flagList.add(new FlagPicture("DKK","dk"));
        flagList.add(new FlagPicture("KZT","kz"));
        flagList.add(new FlagPicture("CAD","cd"));
        flagList.add(new FlagPicture("KGS","kg"));
        flagList.add(new FlagPicture("CNY","cn"));
        flagList.add(new FlagPicture("MDL","md"));
        flagList.add(new FlagPicture("NOK","no"));
        flagList.add(new FlagPicture("PLN","pl"));
        flagList.add(new FlagPicture("RON","ro"));
        flagList.add(new FlagPicture("XDR","xd"));
        flagList.add(new FlagPicture("SGD","sg"));
        flagList.add(new FlagPicture("TJS","tj"));
        flagList.add(new FlagPicture("TRY","tr"));
        flagList.add(new FlagPicture("TMT","tm"));
        flagList.add(new FlagPicture("UZS","uz"));
        flagList.add(new FlagPicture("UAH","ua"));
        flagList.add(new FlagPicture("CZK","cz"));
        flagList.add(new FlagPicture("SEK","se"));
        flagList.add(new FlagPicture("CHF","ch"));
        flagList.add(new FlagPicture("ZAR","za"));
        flagList.add(new FlagPicture("KRW","kr"));
        flagList.add(new FlagPicture("JPY","jp"));
    }

    public List<FlagPicture> getFlagList()
    {
        return flagList;
    }

    public List<BankPlace> getBankPlaces()
    {
        return bankPlaces;
    }
    public List<GetCurrency> getCurrencies()
    {
        return currencies;
    }

    public BankPlace getBankPlace(int id)
    {
        return bankPlaces.get(id);
    }
    public GetCurrency getCurrency(int id)
    {
        return currencies.get(id);
    }

    public void setBankPlace(int id, BankPlace bankPlace)
    {
        bankPlaces.set(id, bankPlace);
    }
    public void setCurrency(int id, GetCurrency currency)
    {
        currencies.set(id, currency);
    }

    public void addBankPlace(BankPlace bankPlace)
    {
        bankPlaces.add(bankPlace);
    }
    public void addCurrency(GetCurrency currency)
    {
        currencies.add(currency);
    }

    public int sizeBankPlace()
    {
        return bankPlaces.size();
    }
    public int sizeCurrency()
    {
        return currencies.size();
    }

    public void clearBankPlace()
    {
        bankPlaces.clear();
    }
    public void clearCurrency()
    {
        currencies.clear();
    }
}
