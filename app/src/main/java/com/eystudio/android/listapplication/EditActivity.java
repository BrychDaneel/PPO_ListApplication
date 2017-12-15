package com.eystudio.android.listapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.eystudio.android.listapplication.data.IImageSource;
import com.eystudio.android.listapplication.data.SingletonImageSource;

public class EditActivity extends Activity
        implements View.OnClickListener{

    public static final int ADD_ITEM_RCODE = 1;
    public static final int EDIT_ITEM_RCODE = 2;
    public static final String RET_ITEM_KEY = "com.eystudio.listapplication.edit.ret_item";
    public static final String ITEM_KEY = "com.eystudio.listapplication.edit.item";
    public static final String RCODE_KEY = "com.eystudio.listapplication.edit.rcode";

    private final String DEFAULT_TEXT = "something";
    private final int DEFAULT_PICTURE = 0;

    IImageSource imageSource;

    private EditText nameEdit;
    private ImageView picture;
    private Button image_button;
    private Button ok_button;
    private Button cancel_button;
    private Item item;

    private  void getElements(){
        nameEdit = (EditText) findViewById(R.id.name_edit);
        picture = (ImageView) findViewById(R.id.picture);
        image_button = (Button) findViewById(R.id.image_button);
        ok_button = (Button) findViewById(R.id.ok_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);
    }

    private void setup(){
        imageSource = SingletonImageSource.getInstance();
        if (getIntent().getIntExtra(RCODE_KEY, 0) == EDIT_ITEM_RCODE)
            item = (Item) getIntent().getSerializableExtra(ITEM_KEY);
        else
            item = new Item(DEFAULT_TEXT, DEFAULT_PICTURE);

        syncView();
    }

    private void syncView(){
        nameEdit.setText(item.getName());
        int imageId = imageSource.getImageId(item.getImage());
        picture.setImageResource(imageId);
    }

    private void ok(){
        item.setName(nameEdit.getText().toString());
        Intent intent = new Intent();
        intent.putExtra(RET_ITEM_KEY, item);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void cancel(){
        setResult(RESULT_CANCELED);
        finish();
    }

    private void bind(){
        image_button.setOnClickListener(this);
        ok_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
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
            case R.id.image_button: changeImage(); break;
            case R.id.cancel_button: cancel(); break;
            case R.id.ok_button: ok(); break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePickerActivity.SELECT_IMAGE_RCODE && resultCode == RESULT_OK){
            int image= data.getIntExtra(ImagePickerActivity.IMAGE_ID_RET, DEFAULT_PICTURE);
            item.setImage(image);
            item.setName(nameEdit.getText().toString());
            syncView();
        }
    }


}
