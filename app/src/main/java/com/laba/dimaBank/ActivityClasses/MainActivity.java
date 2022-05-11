package com.laba.dimaBank.ActivityClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.laba.dimaBank.Currencies.Valute;
import com.laba.dimaBank.Currencies.ValuteXmlParser;
import com.laba.dimaBank.Downloader;
import com.laba.dimaBank.MyApp;
import com.laba.dimaBank.R;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    final Context context = this;
    TextView usdTextView;
    TextView eurTextView;
    TextView dateTextView;
    protected MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApp = (MyApp)getApplicationContext();

        usdTextView = (TextView) findViewById(R.id.main_usd);
        eurTextView = (TextView) findViewById(R.id.main_eur);
        dateTextView = (TextView) findViewById(R.id.main_date);


        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Downloader dw = new Downloader();
                    String xmlFile = dw.downloadXMLFile();
                    DecimalFormat decimalFormat = new DecimalFormat("##.00");

                    dateTextView.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            dateTextView.setText(dw.getTodayDate().replace('/', '.'));
                        }
                    });

                    usdTextView.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ValuteXmlParser xParser = new ValuteXmlParser();
                            if(xParser.parse(xmlFile))
                            {
                                ArrayList<Valute> valutes = xParser.getValutes();
                                for (Valute valute : valutes)
                                {
                                    // Получение валюты долара на основную страницу
                                    if ("usd".equalsIgnoreCase(valute.getCharCode()))
                                    {
                                        double usdValue = valute.getValue();
                                        usdTextView.setText(decimalFormat.format(usdValue));

                                    // Получение валюты евро на основную страницу
                                    }
                                    else if ("eur".equalsIgnoreCase(valute.getCharCode()))
                                    {
                                        double eurValue = valute.getValue();
                                        eurTextView.setText(decimalFormat.format(eurValue));
                                    }
                                }
                            }
                        }
                    });
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void openFirstActivity(View view)
    {
        Intent intent = new Intent(this, First.class);
        startActivity(intent);
    }

    public void openSecondActivity(View view)
    {
        Intent intent = new Intent(this, Second.class);
        startActivity(intent);
    }
    public void openThirdActivity(View view)
    {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.auth_alert_form, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);

        final Button userInput = (Button) promptsView.findViewById(R.id.btn_three);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog,int id)
                            {
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}