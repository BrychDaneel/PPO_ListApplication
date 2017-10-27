package com.eystudio.android.listapplication;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by daneel on 27.10.17.
 */

public class ImageItemViewController {
    View view;
    int position;
    IImageSource imageSource;

    ImageButton imageButton;

    void getElements(){
        imageButton = view.findViewById(R.id.image_item);
    }

    void bind(){

    }

    public void updateContent(int position){
        int imageId = imageSource.getImageId(position);
        imageButton.setImageResource(imageId);
    }

    public ImageItemViewController(int position, View view, IImageSource imageSource) {
        this.view = view;
        this.position = position;
        this.imageSource = imageSource;
        getElements();
        bind();
        updateContent(position);
    }
}
