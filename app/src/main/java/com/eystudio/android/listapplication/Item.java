package com.eystudio.android.listapplication;

/**
 * Created by daneel on 27.10.17.
 */

public class Item {
    String mName;
    int mPicture;
    int mId;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmImage() {
        return mPicture;
    }

    public void setmImage(int mImage) {
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
}
