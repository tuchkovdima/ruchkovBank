package com.laba.dimaBank.controller;

import com.laba.dimaBank.model.GetMoney;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

public class MoneyXmlParser
{

    // Список валют после обработки
    private ArrayList<GetMoney> valutes;

    public MoneyXmlParser()
    {
        valutes = new ArrayList<>();
    }

    public ArrayList<GetMoney> getValutes()
    {
        return valutes;
    }

    // Преобразование файла XML к списку объектов и возращаем результат успешно или нет
    public boolean parse(String xmlData)
    {
        boolean status = true;
        GetMoney currentValute = null;
        boolean inEntry = false;
        String textValue = "";

        try
        {
            // Создаём необходимые переменные для работы с XML файлом
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            // Настраиваем обработчик на наш файл
            xpp.setInput(new StringReader(xmlData));

            // Получаем текущее состояние процесса чтения документа XML
            int eventType = xpp.getEventType();

            // Пока документ не закончился
            while(eventType != XmlPullParser.END_DOCUMENT){
                // Получаем название тэга
                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG: // Если натолкнулись на начала
                        if("valute".equalsIgnoreCase(tagName))
                        {  // Проверяем название тэга, чтобы он совпадал с "valute" (РЕГИСТР НЕ ВАЖЕН)
                            inEntry = true; // Если условие верно, отмечаем переменную true - записывается новый объект
                            currentValute = new GetMoney();// Очищаем currentValute и подготавливаем его для новой записи валюты
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();// Запоминаем в переменной данные между тэгов
                        break;
                    case XmlPullParser.END_TAG: // Если тэг закрывается
                        if(inEntry)
                        {   // Проверяем, собираем ли мы данные для новой валюты?
                            if("valute".equalsIgnoreCase(tagName))
                            { // Если закрывающийся тэг это тэг валюты, тогда..
                                valutes.add(currentValute);// Добавляем валюту в список
                                inEntry = false;// И устанавливаем флаг, что валюта была записана

                            // Если это другие тэги, тогда записываем соответсвенно информацию
                            }
                            else if("numcode".equalsIgnoreCase(tagName))
                            {
                                currentValute.setNumCode(Integer.parseInt(textValue));
                            }
                            else if("charcode".equalsIgnoreCase(tagName))
                            {
                                currentValute.setCharCode(textValue);
                            }
                            else if("nominal".equalsIgnoreCase(tagName))
                            {
                                currentValute.setNominal(Integer.parseInt(textValue));
                            }
                            else if("name".equalsIgnoreCase(tagName))
                            {
                                currentValute.setName(textValue);
                            }
                            else if("value".equalsIgnoreCase(tagName))
                            {
                                // При записис Значения стоимости - преобразовываем из строки в дробное число и округляем её
                                currentValute.setValue(Math.round(Double.parseDouble(textValue.replace(',','.')) * 100.0) / 100.0);   // заменяем , на . так как для парса в Double нужна там .
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();     // Обновляем текущее состояние процесса чтения документа XML
            }
        }
        catch (Exception e)
        {
            status = false;
            e.printStackTrace();
        }
        return status;          // Возващаем результат - успешно или нет
    }
}