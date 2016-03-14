package com.example.aayushma.worklistmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;

import com.example.aayushma.worklistmanager.fragment.AddFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by aayushma on 2/29/2016.
 */
public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

        //new Date().se
        AddActivity.dateAndTime.setText(day + "/" + (month + 1) + "/" + year);
    }
}


