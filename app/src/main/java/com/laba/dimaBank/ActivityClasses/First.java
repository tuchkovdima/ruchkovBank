package com.laba.dimaBank.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.laba.dimaBank.BankPlaces.ArrayBankPlaceAdapter;
import com.laba.dimaBank.BankPlaces.BankPlace;
import com.laba.dimaBank.BankPlaces.BankPlaceJSONParser;
import com.laba.dimaBank.Downloader;
import com.laba.dimaBank.MyApp;
import com.laba.dimaBank.R;
import java.io.IOException;
import java.util.ArrayList;

public class First extends AppCompatActivity
{
    protected ListView listView;
    protected TextView textView;
    protected ArrayBankPlaceAdapter adapter;
    protected MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        listView = (ListView) findViewById(R.id.bankPlaceList);
        textView = (TextView) findViewById(R.id.bankPlaceTextView);
        myApp = (MyApp)getApplicationContext();

        adapter = new ArrayBankPlaceAdapter(getApplicationContext());
        listView.setAdapter(adapter);

        textView.setText("Загрузка...");
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    // Загружаем файл JSON
                    String content = new Downloader().downloadJSONFile();
                    listView.post(new Runnable()
                    {
                        public void run()
                        {
                            BankPlaceJSONParser parser = new BankPlaceJSONParser();
                            // Парсим полученные данные в парсере и отдаём ему то что скачали
                            if(parser.parse(content))
                            {
                                // Перед добавление старые записи удаляем
                                myApp.clearBankPlace();

                                // Получем список всех обработанных валют (!)
                                ArrayList<BankPlace> bankPlaces = parser.getBankPlaces();
                                // "Ходим" по бакноотделениям и добавляем их в MyApp для отображения на активности
                                for (BankPlace bankPlace : bankPlaces)
                                {
                                    myApp.addBankPlace(bankPlace);
                                }

                                listView.invalidateViews();
                                textView.setText(R.string.bankPlacesName);
                            }
                        }
                    });
                }
                catch (IOException ex)
                {
                    textView.post(new Runnable()
                    {
                        public void run() {
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
