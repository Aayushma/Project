package com.example.aayushma.worklistmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aayushma.worklistmanager.R;
import com.example.aayushma.worklistmanager.Todos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aayushma on 3/1/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    private Todos todos;
    private int itemLayout;
    public ArrayList<Todos> list=new ArrayList<>();
    OnItemClickListner onItemClickListner;

    Context context;

    public RecyclerAdapter(Context mcontext, ArrayList<Todos> list) {
        this.list = list;
        this.context = mcontext;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
       final Todos todos=list.get(holder.getAdapterPosition());
        holder.lists.setText(list.get(position).getLists());
        holder.description.setText(list.get(position).getDescription());
        holder.dateandtime.setText(list.get(position).getDatetime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onItemClick(todos);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onItemClickListner.onItemLongClick(todos,position);
                return  true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnClickListner(OnItemClickListner onClickListner){
        this.onItemClickListner =onClickListner;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView lists, description, dateandtime;

        public MyHolder(View itemView) {
            super(itemView);
            lists = (TextView) itemView.findViewById(R.id.lists);
            description = (TextView) itemView.findViewById(R.id.description);
            dateandtime=(TextView) itemView.findViewById(R.id.dateandtime);
        }
    }


    public interface  OnItemClickListner{
        public void onItemClick(Todos todos);
        public void onItemLongClick(Todos todos,int pos);
    }

}
