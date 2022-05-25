package com.laba.dimaBank.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.laba.dimaBank.model.BankPlace;
import com.laba.dimaBank.model.BankPlaceJSONParser;
import com.laba.dimaBank.model.Downloader;
import com.laba.dimaBank.model.PictureNamePlus;
import com.laba.dimaBank.R;
import java.io.IOException;
import java.util.ArrayList;

public class BranchesAndAtms extends AppCompatActivity
{
    protected ListView listView;
    protected TextView textView;
    protected ArrayBankPlaceAdapter adapter;
    private Thread downloadThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchesandatms);
        listView = (ListView) findViewById(R.id.bankPlaceList);
        textView = (TextView) findViewById(R.id.bankPlaceTextView);
        PictureNamePlus pictureNamePlus = (PictureNamePlus)getApplicationContext();

        adapter = new ArrayBankPlaceAdapter(getApplicationContext());
        listView.setAdapter(adapter);

        textView.setText("Загрузка...");
        downloadThread = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    // Загружаем файл JSON
                    String content = new Downloader().downloadJSONFile();
                    listView.post( new Runnable() // возвращение обратно в графич
                    {
                        public void run()
                        {
                            BankPlaceJSONParser parser = new BankPlaceJSONParser();
                            // Парсим полученные данные в парсере и отдаём ему то что скачали
                            if(parser .
                                    parse(content))
                            {
                                // Перед добавление старые записи удаляем
                                pictureNamePlus.clearBankPlace();

                                // Получем список всех обработанных валют (!)
                                ArrayList<BankPlace> bankPlaces = parser.getBankPlaces();
                                // "Ходим" по бакноотделениям и добавляем их в MyApp для отображения на активности
                                for (BankPlace bankPlace : bankPlaces)
                                {
                                    pictureNamePlus.addBankPlace(bankPlace);
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
                        public void run()
                        {
                            textView.setText("Ошибка");
                            System.out.println("Ошибка" + ex.getMessage());
                        }
                    });
                }
            }
        });
        downloadThread.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onDestroy() {
        downloadThread.interrupt();
        super.onDestroy();
    }

}
