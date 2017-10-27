package com.eystudio.android.listapplication;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by daneel on 27.10.17.
 */

public class ItemViewController {
    private View view;
    private int position;
    private IItemStorage storage;
    private IImageSource imageSource;

    private TextView name;
    private ImageView picture;

    private void getElements(){
        name = view.findViewById(R.id.item_name);
        picture = view.findViewById(R.id.picture);
    }

    private void bind(){

    }

    public void updateContent(int position){
        Item item = storage.getItem(position);
        name.setText(item.getName());
        int imageId = imageSource.getImageId(item.getImage());
        picture.setImageResource(imageId);
    }

    public ItemViewController(int position, View view,
                              IItemStorage storage, IImageSource imageSource) {
        this.view = view;
        this.position = position;
        this.storage = storage;
        this.imageSource = imageSource;
        getElements();
        bind();
        updateContent(position);
    }
}
