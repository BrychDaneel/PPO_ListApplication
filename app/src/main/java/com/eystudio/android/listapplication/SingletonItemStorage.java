package com.eystudio.android.listapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daneel on 27.10.17.
 */

public class SingletonItemStorage implements IItemStorage{

    static SingletonItemStorage instance;
    List<Item> storage = new ArrayList<>();

    private SingletonItemStorage(){
        for (int i=0; i<20; i++)
            storage.add(new Item(Integer.toString(i), i));
    }

    public static SingletonItemStorage getInstance(){
        if (instance == null)
            instance = new SingletonItemStorage();
        return instance;
    }

    public int getCount(){
        return storage.size();
    }

    public Item getItem(int i){
        return storage.get(i);
    }

    public void save(){

    }

    public void addItem(Item item){
        storage.add(item);
        item.setId(storage.size() - 1);
    }
}
