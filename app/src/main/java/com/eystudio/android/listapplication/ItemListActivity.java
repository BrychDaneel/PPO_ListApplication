package com.eystudio.android.listapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ItemListActivity extends AppCompatActivity {

    ListView list;
    ItemAdapter adapter;

    void getElements(){
        list = (ListView) findViewById(R.id.item_list);
    }

    void bind(){

    }

    void setup(){
        adapter = new ItemAdapter(this, SingletonItemStorage.getInstance());
        list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getElements();
        setup();
        bind();
    }
}
