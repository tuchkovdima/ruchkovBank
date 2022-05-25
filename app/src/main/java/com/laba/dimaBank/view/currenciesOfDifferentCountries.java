package com.laba.dimaBank.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.laba.dimaBank.controller.ArrayCurrencyAdapter;
import com.laba.dimaBank.model.GetCurrency;
import com.laba.dimaBank.model.GetMoney;
import com.laba.dimaBank.controller.MoneyXmlParser;
import com.laba.dimaBank.model.Downloader;
import com.laba.dimaBank.model.PictureNamePlus;
import com.laba.dimaBank.R;
import java.io.IOException;
import java.util.ArrayList;

public class currenciesOfDifferentCountries extends AppCompatActivity
{
    protected ListView listView;
    protected TextView textView;
    protected ArrayCurrencyAdapter adapter;
    protected PictureNamePlus pictureNamePlus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currenciesofdifferentcountries);
        listView = (ListView) findViewById(R.id.currencyList);
        textView = (TextView) findViewById(R.id.currencyDate);
        pictureNamePlus = (PictureNamePlus)getApplicationContext();

        adapter = new ArrayCurrencyAdapter(getApplicationContext());
        listView.setAdapter(adapter);

        textView.setText("Загрузка...");
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    // Загружаем файл XML
                    String content = new Downloader().downloadXMLFile();
                    listView.post(new Runnable()
                    {
                        public void run()
                        {
                            MoneyXmlParser parser = new MoneyXmlParser();
                            // Парсим полученные данные в парсере и отдаём ему то что скачали
                            if(parser.parse(content))
                            {
                                double koff = 0.1;
                                // Перед добавление старые записи удаляем
                                pictureNamePlus.clearCurrency();

                                // Получем список всех обработанных валют (!)
                                ArrayList<GetMoney> valutes = parser.getValutes();
                                // "Ходим" по валютам и добавляем их в отдельный массив объектов и добавляем данные как нам нужно
                                for (GetMoney valute : valutes)
                                {
                                    double value = valute.getValue();
                                    // Новый объект валюты для отображения
                                    GetCurrency currency = new GetCurrency(
                                            valute.getCharCode(),
                                            valute.getName() + (valute.getNominal() != 1 ? " x" + valute.getNominal() : ""),
                                            value,
                                            value + value * koff
                                    );

                                    // добавляем их в MyApp для отображения на активности
                                    pictureNamePlus.addCurrency(currency);
                                }

                                listView.invalidateViews();
                                textView.setText(new Downloader().getTodayDate().replace('/', '.'));
                            }
                        }
                    });
                }
                catch (IOException ex)
                {
                    textView.post(new Runnable()
                    {
                        public void run()
                        {
                            textView.setText("Ошибка");
                            System.out.println("Ошибка" + ex.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
