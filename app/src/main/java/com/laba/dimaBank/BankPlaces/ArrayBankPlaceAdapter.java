package com.laba.dimaBank.BankPlaces;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.laba.dimaBank.MyApp;
import com.laba.dimaBank.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ArrayBankPlaceAdapter extends BaseAdapter
{
    private Context context;
    private MyApp myApp;

    public ArrayBankPlaceAdapter(Context context)
    {
        this.context = context;
        myApp = (MyApp) context.getApplicationContext();
    }

    @Override
    public int getCount()
    {
        return myApp.sizeBankPlace();
    }

    @Override
    public Object getItem(int position)
    {
        return myApp.getBankPlace(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Context context = parent.getContext();
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_bank_place_item, parent, false);
        }

        LinearLayout liner = (LinearLayout) convertView;
        TextView textViewBankPlaceAddress = (TextView) liner.findViewById(R.id.bankPlaceAddress);
        TextView textViewBankPlaceType = (TextView) liner.findViewById(R.id.bankPlaceType);
        TextView textViewBankPlaceState = (TextView) liner.findViewById(R.id.bankPlaceState);
        TextView textViewBankPlaceWorkHours = (TextView) liner.findViewById(R.id.bankPlaceWorkHours);

        BankPlace currentBankPlace = myApp.getBankPlace(position);
        String currentTime = getCurrentTime();

        textViewBankPlaceAddress.setText(currentBankPlace.getBankPlaceAddress());
        textViewBankPlaceType.setText(getBankPlaceTypeInString(currentBankPlace.getBankPlaceType()));
        textViewBankPlaceState.setText(getBankPlaceStateInString(currentBankPlace.getBankPlaceStartTime(), currentBankPlace.getBankPlaceCloseTime(), currentTime));
        textViewBankPlaceState.setTextColor(getBankPlaceStateInColor(currentBankPlace.getBankPlaceStartTime(), currentBankPlace.getBankPlaceCloseTime(), currentTime));
        textViewBankPlaceWorkHours.setText(getBankPlaceWorkHoursInString(currentBankPlace.getBankPlaceStartTime(), currentBankPlace.getBankPlaceCloseTime()));

        return convertView;
    }

    private String getBankPlaceTypeInString(String bankPlaceType)
    {
        switch (bankPlaceType)
        {
            case "0": return "Банкомат";
            case "1": return "Отделение";
            default: return "Не указано";
        }
    }

    private String getBankPlaceStateInString(String startTime, String closeTime, String currentTime)
    {
        int startTimeValue = getMinutesFromTime(startTime);
        int closeTimeValue = getMinutesFromTime(closeTime);
        int currentTimeValue = getMinutesFromTime(currentTime);

        if (startTimeValue == closeTimeValue)
        {
            return "Открыто";
        }
        else if (currentTimeValue >= startTimeValue)
        {
            if (currentTimeValue < closeTimeValue)
            {
                return "Открыто";
            }
        }

        return "Закрыто";
    }

    private int getBankPlaceStateInColor(String startTime, String closeTime, String currentTime)
    {
        int startTimeValue = getMinutesFromTime(startTime);
        int closeTimeValue = getMinutesFromTime(closeTime);
        int currentTimeValue = getMinutesFromTime(currentTime);

        if (startTimeValue == closeTimeValue)
        {
            return Color.GREEN;
        }
        else if (currentTimeValue >= startTimeValue)
        {
            if (currentTimeValue < closeTimeValue)
            {
                return Color.GREEN;
            }
        }

        return Color.RED;
    }

    private String getBankPlaceWorkHoursInString(String startTime, String closeTime)
    {
        return "Часы работы " + startTime + "-" + closeTime;
    }

    private String getCurrentTime()
    {
        Calendar calender = Calendar.getInstance();
        System.out.println("Current time => "+calender.getTime());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(calender.getTime());
        return formattedDate;
    }

    private int getMinutesFromTime(String stringTime)
    {
        int value = -1;
        String[] d = stringTime.split(":");
        if (d.length == 2)
        {
            value = Integer.parseInt(d[1]) + Integer.parseInt(d[0]) * 60;
        }

        return value;
    }
}
