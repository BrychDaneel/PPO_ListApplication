package com.eystudio.android.listapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int ADD_ITEM_RCODE = 1;

    private final String DEFAULT_TEXT = "something";
    private final int DEFAULT_PICTURE = 0;

    IImageSource imageSource;

    private EditText nameEdit;
    private ImageView picture;
    private Button image_button;
    private Item item;

    private  void getElements(){
        nameEdit = (EditText) findViewById(R.id.name_edit);
        picture = (ImageView) findViewById(R.id.picture);
        image_button = (Button) findViewById(R.id.image_button);
    }

    private void setup(){
        imageSource = SingletonImageSource.getInstance();
        item = new Item(DEFAULT_TEXT, DEFAULT_PICTURE);
        syncItem();
    }

    private void syncItem(){
        nameEdit.setText(item.getName());
        int imageId = imageSource.getImageId(item.getImage());
        picture.setImageResource(imageId);
    }

    private void bind(){
        image_button.setOnClickListener(this);
    }

    private void changeImage(){
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, ImagePickerActivity.SELECT_IMAGE_RCODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getElements();
        setup();
        bind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_button:
                changeImage();
                break;
        }
    }
}
