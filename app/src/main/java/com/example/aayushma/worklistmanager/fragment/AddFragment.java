package com.example.aayushma.worklistmanager.fragment;



import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.aayushma.worklistmanager.DatePickerFragment;
import com.example.aayushma.worklistmanager.R;
import com.example.aayushma.worklistmanager.TimePickerFragment;
import com.example.aayushma.worklistmanager.Todos;
import com.example.aayushma.worklistmanager.database.DatabaseManager;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends DialogFragment {
    public static EditText name;
    public static EditText description;
    public static EditText dateAndTime;
    public static Button save;
    String strName;
    String strDescription;
    String strDateAndTime;

    ArrayList<String> todos = new ArrayList<>();


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        getDialog().setTitle("Add Lists");
        name = (EditText) rootView.findViewById(R.id.name);
        description = (EditText) rootView.findViewById(R.id.description);
        dateAndTime = (EditText) rootView.findViewById(R.id.dateandtime);
        save=(Button) rootView.findViewById(R.id.save);
        strName = name.getText().toString();
        strDescription = description.getText().toString();
        dateAndTime.setOnClickListener(onEditTextClickListener);
        strDateAndTime=dateAndTime.getText().toString();

        save.setOnClickListener(onButtonClickListener);

        return rootView;
    }
View.OnClickListener onEditTextClickListener=new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showTruitonTimePickerDialog(v);
        showTruitonDatePickerDialog(v);
    }
};
    View.OnClickListener onButtonClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Todos todos=new Todos();
            todos.setLists(strName);
            todos.setDescription(strDescription);
            todos.setDatetime(strDateAndTime);
            DatabaseManager.insertData(todos);
        }
    };
    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

}