package com.example.aayushma.worklistmanager;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aayushma.worklistmanager.adapter.RecyclerAdapter;
import com.example.aayushma.worklistmanager.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    public static EditText name;
    public static EditText description;
    public static EditText dateAndTime;
    public static Button save;
    String strName;
    String strDescription;
    String strDateAndTime;
    DatabaseManager manager;
    Todos mTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        manager = DatabaseManager.getDbManager(AddActivity.this);

        mTodos= (Todos) getIntent().getSerializableExtra("todo");

        initView();

        if(mTodos==null){
            //new insert
        }else{
//update
            name.setText(mTodos.getLists());
            description.setText(mTodos.getDescription());
            dateAndTime.setText(mTodos.getDatetime());
        }
    }




    public AddActivity() {

    }

    public void initView(){
        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        dateAndTime = (EditText) findViewById(R.id.dateandtime);
        save=(Button) findViewById(R.id.save);
        dateAndTime.setOnClickListener(onEditTextClickListener);
        save.setOnClickListener(onButtonClickListener);
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
            strDateAndTime=dateAndTime.getText().toString();
            strName = name.getText().toString();
            strDescription = description.getText().toString();
            Todos todos=new Todos();
            todos.setLists(strName);
            todos.setDescription(strDescription);
            todos.setDatetime(strDateAndTime);

            if(mTodos==null) {
                manager.insertData(todos);
                manager.fetchContact();
            }
            else{
                manager.UpdateDb(todos);
                manager.fetchContact();
            }

            startAlert();
            Intent intent=new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);

        }
    };
    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new com.example.aayushma.worklistmanager.TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }





    public void startAlert() {
//        EditText text = (EditText) findViewById(R.id.dateandtime);
//        long i = Long.parseLong(text.getText().toString());
        Calendar c=Calendar.getInstance();
        long  time= c.getTimeInMillis();
        long ti=new Date().getTime();
        long timeDifference=ti-time;

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeDifference, pendingIntent);
       // Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
    }
}
