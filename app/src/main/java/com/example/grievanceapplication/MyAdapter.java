package com.example.grievanceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Students> list;

    public MyAdapter(Context context, ArrayList<Students> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.data,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Students students=list.get(position);
        holder.name.setText(students.Name);
        holder.title.setText(students.Title);
        holder.description.setText(students.Description);
        holder.branch.setText(students.Branch);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name,title,description,branch;

        public MyViewHolder(@NonNull View view){
            super(view);
            name=view.findViewById(R.id.name);
            title=view.findViewById(R.id.title);
            description=view.findViewById(R.id.description);
            branch=view.findViewById(R.id.branch);
        }
    }
}
