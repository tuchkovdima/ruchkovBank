package com.laba.dimaBank.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BankPlaceJSONParser
{

    private ArrayList<BankPlace> bankPlaces;  // Список сущностей банков и отделений

    public BankPlaceJSONParser()
    {
        bankPlaces = new ArrayList<>();
    }

    public ArrayList<BankPlace> getBankPlaces()
    {
        return bankPlaces;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean parse(String jsonText)
    {
        boolean status = true;
        JSONObject jsonRoot;  //здесь хранится json-объект
        JSONArray jsonArray;  //здесь хранится json-массив

        try
        {
            jsonRoot = new JSONObject(jsonText);  //из строки — содержимого файла создаем программный json-объект
            jsonArray = jsonRoot.getJSONArray("banks"); //извлекаем из json-объекта массив, хранящийся под ключом «banks»
            //JSONObject[] banks = new JSONObject[jsonArray.length()];//создаем массив для хранения json-объектов
            for(int i=0;i < jsonArray.length();i++) {  //цикл для заполнения массива json-объектов
                JSONObject currentJSONBank = jsonArray.getJSONObject(i);  //извлекаем очередной json-объект
                String address = currentJSONBank.getString("address");  // извлекаем из json-объекта строковое поле с ключом «address»
                String type = currentJSONBank.getString("type");  // извлекаем из json-объекта строковое поле с ключом «type»
                String openTime = currentJSONBank.getString("openTime");  // извлекаем из json-объекта строковое поле с ключом «openTime»
                String closeTime = currentJSONBank.getString("closeTime");  // извлекаем из json-объекта строковое поле с ключом «closeTime»
//                String closeTimeStr = currentJSONBank.getString("closeTime");  // извлекаем из json-объекта строковое поле с ключом «closeTime»
                // Хранить время в OffsetTime, сравнивать с текущим временем конвертацией в Instant
//                OffsetTime closeTime = LocalTime.parse(closeTimeStr, DateTimeFormatter.ISO_LOCAL_TIME).atOffset(ZoneId.of("MSK"));
//                OffsetTime.ofInstant(Instant.now(), ZoneId.of("MSK")).compareTo(closeTime);//1 или 0 пример
                //извлеките из json-объекта остальные поля
                bankPlaces.add(new BankPlace( //добавьте извлеченные поля в список отделений/банкоматов
                        address,
                        type,
                        openTime,
                        closeTime));
            }
        }
        catch(Exception e)
        {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}