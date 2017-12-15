package com.eystudio.android.listapplication;

import java.io.Serializable;

/**
 * Created by daneel on 27.10.17.
 */

public class Item implements Serializable{
    String mName;
    int mPicture;
    int mId;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getImage() {
        return mPicture;
    }

    public void setImage(int mImage) {
        this.mPicture = mImage;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Item(int id, String name, int picture){
        mId = id;
        mName = name;
        mPicture = picture;
    }

    public Item(String name, int picture){
        this(0, name, picture);
    }
}
