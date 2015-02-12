package com.example.TimeSelector;

import android.content.Context;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

/**
 * Created by Administrator on 2015/2/12.
 */
public class TimeSelector extends FrameLayout {
    NumberPicker npYear,npMonth,npDay,npHour,npMinute,npSecond;
    int year,month,day,hour,minute,second;
    public TimeSelector(Context context)
    {
        this(context, null);

    }
    public TimeSelector(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }
    public TimeSelector(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        View v = LayoutInflater.from(context).inflate(R.layout.time_selector,this);
        npYear = (NumberPicker)v.findViewById(R.id.year_picker);
        npMonth = (NumberPicker)v.findViewById(R.id.month_picker);
        npDay = (NumberPicker)v.findViewById(R.id.day_picker);
        npHour = (NumberPicker)v.findViewById(R.id.hour_picker);
        npMinute = (NumberPicker)v.findViewById(R.id.minute_picker);
        npSecond = (NumberPicker)v.findViewById(R.id.second_picker);

        init();
    }

    public void init(){
        Time time = new Time("GMT+8");
        time.setToNow();
        year = time.year;
        month = time.month + 1;
        day = time.monthDay;
        hour = time.hour + 8;
        minute = time.minute;
        second = time.second;

        initBasicSettings(year,month,day,hour,minute,second);
        setTime();

        /**
         *change scale when date is changed
         */
        npYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                year  = i1;
                initBasicSettings(year,month,day,hour,minute,second);
                setTime();
            }
        });
        npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                month = i1;
                initBasicSettings(year,month,day,hour,minute,second);
                setTime();
            }
        });
        npDay.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                day = i1;
                setTime();
            }
        });
        npHour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                hour = i1;
                setTime();
            }
        });
        npMinute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                minute = i1;
                setTime();
            }
        });
        npSecond.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                second = i1;
                setTime();
            }
        });
    } public void initBasicSettings(int year,int month,int day,int hour,int minute,int second){
        /**
         * basic settings
         */
        npYear.setMinValue(1970);
        npYear.setMaxValue(2100);
        npMonth.setMinValue(0);
        npMonth.setMaxValue(12);
        npDay.setMinValue(1);
        switch (month)
        {   //set days is 28 days or 29 when February
            case 1:
            case 3:
            case 5:
            case 7:
            case 10:
            case 12:
                npDay.setMaxValue(31);break;
            case 2:
                if (year%100 == 0){
                    if(year%400 == 0){
                        npDay.setMaxValue(29);
                    }
                }else{
                    if(year%4 == 0){
                        npDay.setMaxValue(29);
                    }
                    else {
                        npDay.setMaxValue(28);
                    }
                }
                break;
            default:
                npDay.setMaxValue(30);break;
        }
        npHour.setMinValue(0);
        npHour.setMaxValue(23);
        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);
        npSecond.setMinValue(0);
        npSecond.setMaxValue(59);
    }
    public void setTime()
    {
        /**
         * set time to now
         */
        npYear.setValue(year);
        npMonth.setValue(month);
        npDay.setValue(day);
        npHour.setValue(hour);
        npMinute.setValue(minute);
        npSecond.setValue(second);
    }


}
