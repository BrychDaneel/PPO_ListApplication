package com.eystudio.android.listapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.eystudio.android.listapplication.data.IImageSource;

/**
 * Created by daneel on 27.10.17.
 */

public class ImageItemViewController implements View.OnClickListener{
    View view;
    int position;
    IImageSource imageSource;

    ImageButton imageButton;

    void getElements(){
        imageButton = view.findViewById(R.id.image_item);
    }

    void bind(){
        imageButton.setOnClickListener(this);
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

    private void pick(){
        Intent intent = new Intent();
        intent.putExtra(ImagePickerActivity.IMAGE_ID_RET, position);
        Activity activity = (Activity) view.getContext();
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_item: pick(); break;
        }
    }
}
