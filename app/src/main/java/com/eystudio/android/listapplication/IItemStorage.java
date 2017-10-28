package com.eystudio.android.listapplication;

/**
 * Created by daneel on 27.10.17.
 */

interface IItemStorage {
    int getCount();
    Item getItem(int i);
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(Item item);
    void save();
}
