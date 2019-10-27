package com.delight.lesson7_fragment_m3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    ArrayList<String> data;
    private iVhListener listener;
    public MainAdapter(iVhListener listener){
        data = new ArrayList<>();
        this.listener = listener;
    }

    public  void setData(ArrayList<String> data){
        this.data = data;
        notifyDataSetChanged();
    }
    public void deleteString (int position){
        data.remove(position);
        notifyDataSetChanged();
    }


    public void addString(String s){
        data.add(s);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.view_holder_main,parent,false);
        MainViewHolder vh = new MainViewHolder(v);
        vh.listener = listener;
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(data.get(position),position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

