package com.example.aayushma.worklistmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aayushma.worklistmanager.adapter.RecyclerAdapter;
import com.example.aayushma.worklistmanager.database.DatabaseManager;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class UdFragment extends DialogFragment {
    LinearLayout delete;
    LinearLayout edit;
    int pos;
    Todos mTodos;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Todos> list = new ArrayList<>();
    DatabaseManager databaseManager;
    RecyclerAdapter.OnItemClickListner onItemClickListner;

    public UdFragment(RecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
    }

    public UdFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_ud_fragment, container,
                false);
        getDialog().setTitle("Delete or Update");
        delete = (LinearLayout) rootView.findViewById(R.id.delete);
        edit = (LinearLayout) rootView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AddActivity.class);
                intent.putExtra("todo",mTodos);
                startActivity(intent);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager.getDbManager(getContext()).DeleteFromDb(mTodos.getLists());
                recyclerAdapter.list.remove(pos);
                recyclerAdapter.notifyItemRemoved(pos);
                dismiss();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTodos = (Todos) getArguments().getSerializable("todo");
        pos = getArguments().getInt("pos");

    }


}
