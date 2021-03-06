package com.eystudio.android.listapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.eystudio.android.listapplication.data.DatabaseItemStorage;
import com.eystudio.android.listapplication.data.IImageSource;
import com.eystudio.android.listapplication.data.IItemStorage;
import com.eystudio.android.listapplication.data.SingletonImageSource;

public class ItemListActivity extends AppCompatActivity{

    ListView list;
    ItemAdapter adapter;
    IItemStorage storage;
    IImageSource imageSource;

    void getElements(){
        list = (ListView) findViewById(R.id.item_list);
    }

    void bind(){
    }

    void setup(){
        storage = new DatabaseItemStorage(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_new:
                addItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem(){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(EditActivity.RCODE_KEY, EditActivity.ADD_ITEM_RCODE);
        startActivityForResult(intent, EditActivity.ADD_ITEM_RCODE);
    }

    public void onRemoveConfirmed(Item item){
        storage.deleteItem(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EditActivity.ADD_ITEM_RCODE && resultCode == RESULT_OK) {
            Item item = (Item) data.getSerializableExtra(EditActivity.RET_ITEM_KEY);
            storage.addItem(item);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == EditActivity.EDIT_ITEM_RCODE && resultCode == RESULT_OK) {
            Item item = (Item) data.getSerializableExtra(EditActivity.RET_ITEM_KEY);
            storage.updateItem(item);
            adapter.notifyDataSetChanged();
        }
    }
}
