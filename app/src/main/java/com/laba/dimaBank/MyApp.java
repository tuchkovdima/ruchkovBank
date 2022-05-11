package com.laba.dimaBank;

import android.app.Application;
import com.laba.dimaBank.BankPlaces.BankPlace;
import com.laba.dimaBank.Currencies.Currency;
import com.laba.dimaBank.Currencies.Flag;
import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application
{

    private final List<BankPlace> bankPlaces = new ArrayList<>();
    private final List<Currency> currencies = new ArrayList<>();
    private final List<Flag>flagList=new ArrayList<>();

    public MyApp()
    {
        super();
        flagsInit();        // Инициализация флагов - составление сопоставлений (Название валюты <=> Название страны)
    }

    // Инициализация флагов - составление сопоставлений (Название валюты <=> Название страны)
    private void flagsInit()
    {

        flagList.add(new Flag("AUD","ai"));
        flagList.add(new Flag("GBP","uk"));
        flagList.add(new Flag("BYR","by"));
        flagList.add(new Flag("HKD","hk"));
        flagList.add(new Flag("USD","us"));
        flagList.add(new Flag("EUR","eu"));
        flagList.add(new Flag("RUB","ru"));
        flagList.add(new Flag("AZN","az"));
        flagList.add(new Flag("AMD","am"));
        flagList.add(new Flag("BYN","by"));
        flagList.add(new Flag("BGN","bg"));
        flagList.add(new Flag("BRL","br"));
        flagList.add(new Flag("HUF","hu"));
        flagList.add(new Flag("DKK","dk"));
        flagList.add(new Flag("KZT","kz"));
        flagList.add(new Flag("CAD","cd"));
        flagList.add(new Flag("KGS","kg"));
        flagList.add(new Flag("CNY","cn"));
        flagList.add(new Flag("MDL","md"));
        flagList.add(new Flag("NOK","no"));
        flagList.add(new Flag("PLN","pl"));
        flagList.add(new Flag("RON","ro"));
        flagList.add(new Flag("XDR","xd"));
        flagList.add(new Flag("SGD","sg"));
        flagList.add(new Flag("TJS","tj"));
        flagList.add(new Flag("TRY","tr"));
        flagList.add(new Flag("TMT","tm"));
        flagList.add(new Flag("UZS","uz"));
        flagList.add(new Flag("UAH","ua"));
        flagList.add(new Flag("CZK","cz"));
        flagList.add(new Flag("SEK","se"));
        flagList.add(new Flag("CHF","ch"));
        flagList.add(new Flag("ZAR","za"));
        flagList.add(new Flag("KRW","kr"));
        flagList.add(new Flag("JPY","jp"));
    }

    public List<Flag> getFlagList()
    {
        return flagList;
    }

    public List<BankPlace> getBankPlaces()
    {
        return bankPlaces;
    }
    public List<Currency> getCurrencies()
    {
        return currencies;
    }

    public BankPlace getBankPlace(int id)
    {
        return bankPlaces.get(id);
    }
    public Currency getCurrency(int id)
    {
        return currencies.get(id);
    }

    public void setBankPlace(int id, BankPlace bankPlace)
    {
        bankPlaces.set(id, bankPlace);
    }
    public void setCurrency(int id, Currency currency)
    {
        currencies.set(id, currency);
    }

    public void addBankPlace(BankPlace bankPlace)
    {
        bankPlaces.add(bankPlace);
    }
    public void addCurrency(Currency currency)
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
