package com.eystudio.android.listapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eystudio.android.listapplication.Item;

/**
 * Created by daneel on 28.10.17.
 */

public class DatabaseItemStorage implements IItemStorage {

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public int getCount() {
        Cursor cr = db.rawQuery("SELECT COUNT(*) as cnt FROM items", null);
        cr.moveToFirst();
        return cr.getInt(cr.getColumnIndex("cnt"));
    }

    @Override
    public Item getItem(int i) {
        String pos = Integer.toString(i);
        Cursor cr = db.rawQuery("SELECT id, name, picture FROM items as tab1 " +
                        "WHERE "+pos+" IN " +
                        "(SELECT COUNT(*) FROM items as tab2 WHERE tab1.id > tab2.id)",
                        null);
        cr.moveToFirst();
        int id = cr.getInt(cr.getColumnIndex("id"));
        String name = cr.getString(cr.getColumnIndex("name"));
        int picture = cr.getInt(cr.getColumnIndex("picture"));
        return new Item(id, name, picture);
    }

    @Override
    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("picture", item.getImage());
        db.insert("items", null, values);
    }

    @Override
    public void updateItem(Item item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("picture", item.getImage());

        String id = Integer.toString(item.getId());
        db.update("items", values, "id = ?", new String[]{id});
    }

    @Override
    public void deleteItem(Item item) {
        String id = Integer.toString(item.getId());
        db.delete("items",  "id = ?", new String[]{id});
    }

    @Override
    public void save() {

    }

    public DatabaseItemStorage(Context context){
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
}
