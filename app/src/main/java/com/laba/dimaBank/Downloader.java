package com.laba.dimaBank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.net.ssl.HttpsURLConnection;

public class Downloader
{

    private final String jsonFileUrl;
    private final String xmlFileUrl;

    public Downloader()
    {
        // Ссылка с данными отделений и банкоматов
        jsonFileUrl = "https://tuchkovdima.github.io/rpc.json";

        // Ссылка на данные xml + актуальная дата
        xmlFileUrl = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + getTodayDate();
    }

    // Получить текущую дату
    public String getTodayDate()
    {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(calender.getTime());
    }

    // Скачивает JSON объект и помещаем его в строку
    public String downloadJSONFile() throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        InputStream stream = null;
        HttpURLConnection connection = null;
        try
        {
            // Настраиваем подключение перед скачиванием
            URL url = new URL(jsonFileUrl);
            connection = (HttpURLConnection) url.openConnection();
            stream = connection.getInputStream();
            int HttpResult = connection.getResponseCode();
            if(HttpResult == HttpURLConnection.HTTP_OK)
            {
                // Настраиваем поток входных данных
                reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                String line;

                // Считываем все данные в строку (!)
                while ((line=reader.readLine()) != null)
                {
                    stringBuilder.append(line + "\n");
                }
            }
            return stringBuilder.toString();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (stream != null)
            {
                stream.close();
            }
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }

    // Скачивает XML объект и помещаем его в строку
    public String downloadXMLFile() throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try
        {
            // Настраиваем подключение перед скачиванием
            URL url = new URL(xmlFileUrl);
            connection = (HttpsURLConnection) url.openConnection();
            stream = connection.getInputStream();

            // Настраиваем поток входных данных
            reader = new BufferedReader(new InputStreamReader(stream, "windows-1251"));
            String line;

            // Считываем все данные в строку (!)
            while ((line=reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (stream != null)
            {
                stream.close();
            }
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }
}
