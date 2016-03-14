package com.example.aayushma.worklistmanager;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aayushma.worklistmanager.adapter.RecyclerAdapter;
import com.example.aayushma.worklistmanager.database.DatabaseManager;
import com.example.aayushma.worklistmanager.fragment.AddFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends FragmentActivity{
    Todos todos=new Todos();
    RecyclerView recyclerView;
    public static ArrayList<Todos> list=new ArrayList<>();
    public ItemTouchHelper itemTouchHelper;
    public static RecyclerAdapter recyclerAdapter;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WorkList Manager");
        initView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 101);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1 && requestCode==101){
            initView();
        }

    }


public void initView(){
    databaseManager=DatabaseManager.getDbManager(this);
    list= databaseManager.fetchContact();
    recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);
    recyclerAdapter = new RecyclerAdapter(this, list);
    recyclerView.setAdapter(recyclerAdapter);
    recyclerAdapter.setOnClickListner(new RecyclerAdapter.OnItemClickListner() {
        @Override
        public void onItemClick(Todos todos) {

        }

        @Override
        public void onItemLongClick(Todos todos,int pos) {
            DialogFragment fragment = new UdFragment(recyclerAdapter);
            Bundle bundle=new Bundle();
            bundle.putSerializable("todo",todos);
            bundle.putSerializable("pos",pos);
            fragment.setArguments(bundle);
            fragment.show(getSupportFragmentManager(), "updateDelete");
        }
    });
    itemTouchHelper=new ItemTouchHelper(simpleItemTouchCallback);
    itemTouchHelper.attachToRecyclerView(recyclerView);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback= new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Collections.swap(recyclerAdapter.list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
            recyclerAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Todos todos=list.get(viewHolder.getAdapterPosition());
            databaseManager.DeleteFromDb(todos.getLists());
            recyclerAdapter.list.remove(viewHolder.getAdapterPosition());
            recyclerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

        }

    };

}
