package com.laba.dimaBank.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.laba.dimaBank.Currencies.ArrayCurrencyAdapter;
import com.laba.dimaBank.Currencies.Currency;
import com.laba.dimaBank.Currencies.Valute;
import com.laba.dimaBank.Currencies.ValuteXmlParser;
import com.laba.dimaBank.Downloader;
import com.laba.dimaBank.MyApp;
import com.laba.dimaBank.R;
import java.io.IOException;
import java.util.ArrayList;

public class Second extends AppCompatActivity
{
    protected ListView listView;
    protected TextView textView;
    protected ArrayCurrencyAdapter adapter;
    protected MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = (ListView) findViewById(R.id.currencyList);
        textView = (TextView) findViewById(R.id.currencyDate);
        myApp = (MyApp)getApplicationContext();

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
                            ValuteXmlParser parser = new ValuteXmlParser();
                            // Парсим полученные данные в парсере и отдаём ему то что скачали
                            if(parser.parse(content))
                            {
                                double koff = 0.1;
                                // Перед добавление старые записи удаляем
                                myApp.clearCurrency();

                                // Получем список всех обработанных валют (!)
                                ArrayList<Valute> valutes = parser.getValutes();
                                // "Ходим" по валютам и добавляем их в отдельный массив объектов и добавляем данные как нам нужно
                                for (Valute valute : valutes)
                                {
                                    double value = valute.getValue();
                                    // Новый объект валюты для отображения
                                    Currency currency = new Currency(
                                            valute.getCharCode(),
                                            valute.getName() + (valute.getNominal() != 1 ? " x" + valute.getNominal() : ""),
                                            value,
                                            value + value * koff
                                    );

                                    // добавляем их в MyApp для отображения на активности
                                    myApp.addCurrency(currency);
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
