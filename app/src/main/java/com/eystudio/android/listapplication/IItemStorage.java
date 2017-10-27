package com.eystudio.android.listapplication;

/**
 * Created by daneel on 27.10.17.
 */

public interface IItemStorage {
    int getCount();
    Item getItem(int i);
    void save();
}
