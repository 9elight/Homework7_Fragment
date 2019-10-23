package com.delight.lesson7_fragment_m3;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateElement_Fragment extends Fragment {

    private View view;
    private Button button;
    private EditText editText;
    private OnCreateElementListener listener;
    public CreateElement_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_element_, container, false);

        editText = view.findViewById(R.id.setText);
        button = view.findViewById(R.id.save);


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.buttonSavedPressed(editText.getText().toString());
//                ListFragment fragment=new ListFragment();
//                Bundle args = new Bundle();
//                args.putString("key",editText.getText().toString());
//                fragment.setArguments(args);

            }
        });





        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateElement_Fragment.OnCreateElementListener) {
            listener = (CreateElement_Fragment.OnCreateElementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CreateElement_Fragment");
        }
    }



    public interface OnCreateElementListener{
        void buttonSavedPressed(String s);
    }

}
