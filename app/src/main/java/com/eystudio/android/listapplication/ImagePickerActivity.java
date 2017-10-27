package com.eystudio.android.listapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class ImagePickerActivity extends AppCompatActivity {

    public static final int SELECT_IMAGE_RCODE = 1;
    public static final String IMAGE_ID_RET = "com.eystudio.listapplication.imagepicker.ret_image";

    GridView grid;
    ImageAdapter adapter;

    void getElements(){
        grid = (GridView) findViewById(R.id.image_picker);
    }

    void bind(){

    }

    void setup(){
        IImageSource imageSource = SingletonImageSource.getInstance();
        adapter = new ImageAdapter(this, imageSource);
        grid.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        getElements();
        setup();
        bind();
    }
}
