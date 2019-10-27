package com.delight.lesson7_fragment_m3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnListFragmentListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements  iVhListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnListFragmentListener mListener;

    private MainAdapter adapter;

    private Button button;





    View view;

    public ListFragment() {
        adapter = new MainAdapter(this);





    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }

        ArrayList<String> strings = DataStore.getmSharedInstance().getSavedStrings(getContext());
        adapter.setData(strings);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

//        Bundle b = getArguments();
//        st = null;
//        if (b != null) {
//            st = b.getString("key");
//        }
//        Log.d("tag2",st);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_list, container, false);

            button = view.findViewById(R.id.addButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        saveNewElem();
//                        mListener.addElementButtonPressed();
                }
            });
        return view;
    }
    public void saveNewElem(){

        Random random = new Random();
        int r = random.nextInt();
       String s = "new elem" + r;
       DataStore.getmSharedInstance().saveString(getContext(),s);


        adapter.addString(s);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentListener) {
            mListener = (OnListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onVhClick(String s) {
        if (mListener != null) {
            mListener.onOpenDetails(s);
        }
    }

    @Override
    public void onDelete(int position) {
        DataStore.getmSharedInstance().deleteString(getContext(),position);
        adapter.deleteString(position);
    }

    public void addElement(String s){
        adapter.addString(s);
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentListener {

        void onOpenDetails(String s);
        void addElementButtonPressed();


    }
}
