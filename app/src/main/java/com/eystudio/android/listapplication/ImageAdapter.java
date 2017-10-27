package com.eystudio.android.listapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by daneel on 27.10.17.
 */

public class ImageAdapter extends BaseAdapter {

    private IImageSource imageSource;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return imageSource.getSize();
    }

    @Override
    public Object getItem(int i) {
        return imageSource.getImageId(i);
    }

    @Override
    public long getItemId(int i) {
        return imageSource.getImageId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            ImageItemViewController controller = (ImageItemViewController) view.getTag();
            controller.updateContent(i);
        } else{
            view = inflater.inflate(R.layout.list_item_image, viewGroup, false);
            ImageItemViewController controller = new ImageItemViewController(i, view, imageSource);
            view.setTag(controller);
        }
        return view;
    }

    public ImageAdapter(Context context, IImageSource imageSource){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageSource = imageSource;
    }
}
