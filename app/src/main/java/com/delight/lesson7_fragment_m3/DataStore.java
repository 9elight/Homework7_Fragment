package com.delight.lesson7_fragment_m3;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class DataStore  {

    private static DataStore mSharedInstance;

    public static DataStore getmSharedInstance(){
     if (mSharedInstance == null){
         mSharedInstance = new DataStore();
     }
     return mSharedInstance;
    }
    private String DATABASE_NAME = "database";
    private String ELEMENTS_KEY = "elems";

    public ArrayList<String> getSavedStrings(Context context){
        SharedPreferences prefs = context.getSharedPreferences(DATABASE_NAME, 0);
        String allElems = prefs.getString(ELEMENTS_KEY,"");
        String[] elemsArray = allElems.split(",");
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(elemsArray));
        return arrayList;
    }

    public void saveString (Context context,String s){

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,0);

        String savedString = sharedPreferences.getString(ELEMENTS_KEY,"");
        if (savedString.equals("")){
            savedString = s;
        }else {
            savedString += "," + s;

        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ELEMENTS_KEY,savedString);
        editor.apply();
    }

    public void deleteString(Context context,int position){
        ArrayList<String>savedStrings = getSavedStrings(context);
        savedStrings.remove(position);

        String savedString = "";

        for (int i = 0; i < savedStrings.size() ; i++) {
            if (i == 0){
                savedString = savedStrings.get(0);
            }else {
                savedString += "," + savedStrings.get(i);
            }
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ELEMENTS_KEY,savedString);
        editor.apply();
    }

}
