package com.delight.lesson7_fragment_m3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MainViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    iVhListener listener;
    private int position;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.vh_text_view);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVhClick(textView.getText().toString());
            }
        });
        Button delete = itemView.findViewById(R.id.vh_delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(position);
            }
        });
    }

    public void onBind(String s, int position) {
        textView.setText(s);
        this.position = position;
    }
}
