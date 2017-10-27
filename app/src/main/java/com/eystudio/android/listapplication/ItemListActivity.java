package com.eystudio.android.listapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ItemListActivity extends AppCompatActivity implements View.OnClickListener{

    ListView list;
    ItemAdapter adapter;
    Button button;
    IItemStorage storage;
    IImageSource imageSource;

    void getElements(){
        list = (ListView) findViewById(R.id.item_list);
        button = (Button) findViewById(R.id.add_button);
    }

    void bind(){
        button.setOnClickListener(this);
    }

    void setup(){
        storage = SingletonItemStorage.getInstance();
        imageSource = SingletonImageSource.getInstance();
        adapter = new ItemAdapter(this, storage, imageSource);
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

    private void addItem(){
        Intent intent = new Intent(this, EditActivity.class);
        startActivityForResult(intent, EditActivity.ADD_ITEM_RCODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_button:
                addItem();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EditActivity.ADD_ITEM_RCODE && resultCode == RESULT_OK) {
            Item item = (Item) data.getSerializableExtra(EditActivity.RET_ITEM_KEY);
            storage.addItem(item);
            adapter.notifyDataSetChanged();
        }
    }
}
