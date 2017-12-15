package com.eystudio.android.listapplication.data;

import com.eystudio.android.listapplication.Item;

/**
 * Created by daneel on 27.10.17.
 */

public interface IItemStorage {
    int getCount();
    Item getItem(int i);
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(Item item);
    void save();
}
