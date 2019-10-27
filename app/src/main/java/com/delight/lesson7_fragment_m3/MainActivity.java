package com.delight.lesson7_fragment_m3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements  ListFragment.OnListFragmentListener,
        DetailsFragment.OnDetailsFragmentListener, CreateElement_Fragment.OnCreateElementListener {
    ListFragment listFragment;
    DetailsFragment detailsFragment;
    CreateElement_Fragment createElement_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        listFragment = ListFragment.newInstance("Start element");
        transaction.replace(R.id.container, listFragment);
        transaction.commit();



    }



    @Override
    public void onOpenDetails(String s) {
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        detailsFragment = DetailsFragment.newInstance(s);
        transaction.replace(R.id.container, detailsFragment);

        transaction.commit();
    }

    @Override
    public void addElementButtonPressed() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.container,new CreateElement_Fragment());
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (detailsFragment != null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container,listFragment);
            transaction.commit();
            detailsFragment = null;
        }
        super.onBackPressed();
    }

    @Override
    public void buttonSavedPressed(String s) {
        listFragment.addElement(s);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,listFragment);
        transaction.commit();

    }
}
