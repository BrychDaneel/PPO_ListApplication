package com.eystudio.android.listapplication;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by daneel on 27.10.17.
 */

public class ItemViewController {
    View view;
    int position;
    IItemStorage storage;

    TextView name;

    void getElements(){
        name = view.findViewById(R.id.item_name);
    }

    void bind(){

    }

    public void updateContent(int position){
        name.setText(storage.getItem(position).getmName());
    }

    public ItemViewController(View view, IItemStorage storage, int position) {
        this.view = view;
        this.position = position;
        this.storage = storage;
        getElements();
        bind();
        updateContent(position);
    }
}
