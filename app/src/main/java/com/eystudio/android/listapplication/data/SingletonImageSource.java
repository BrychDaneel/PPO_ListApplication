package com.eystudio.android.listapplication.data;

import com.eystudio.android.listapplication.R;

/**
 * Created by daneel on 27.10.17.
 */

public class SingletonImageSource implements IImageSource {

    private static SingletonImageSource instance;

    private int[] images = {R.drawable.summer01, R.drawable.summer02, R.drawable.summer03,
        R.drawable.summer04, R.drawable.summer05, R.drawable.summer06, R.drawable.summer07,
        R.drawable.summer08, R.drawable.summer09, R.drawable.summer10, R.drawable.summer11,
        R.drawable.summer12, R.drawable.summer13, R.drawable.summer14, R.drawable.summer15,
        R.drawable.summer16, R.drawable.summer17, R.drawable.summer18, R.drawable.summer19,
        R.drawable.summer20, R.drawable.summer21, R.drawable.summer22, R.drawable.summer23,
        R.drawable.summer24, R.drawable.summer25};

    @Override
    public int getImageId(int position) {
        return images[position];
    }

    public int getSize(){
        return images.length;
    }

    private SingletonImageSource(){};

    public static SingletonImageSource getInstance(){
        if (instance == null)
            instance = new SingletonImageSource();
        return instance;
    }
}
