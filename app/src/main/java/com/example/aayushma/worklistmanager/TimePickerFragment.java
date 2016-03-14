package com.example.aayushma.worklistmanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import com.example.aayushma.worklistmanager.fragment.AddFragment;

import java.util.Calendar;

/**
 * Created by aayushma on 2/29/2016.
 */
public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        AddActivity.dateAndTime.setText(AddActivity.dateAndTime.getText() + " -" + hourOfDay + ":" + minute);
    }
}

