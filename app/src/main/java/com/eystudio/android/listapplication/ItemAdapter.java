package com.eystudio.android.listapplication;

import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.zip.Inflater;

/**
 * Created by daneel on 27.10.17.
 */

public class ItemAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private IItemStorage storage;

    @Override
    public int getCount() {
        return storage.getCount();
    }

    @Override
    public Object getItem(int i) {
        return storage.getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return storage.getItem(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
            view.setTag(new ItemViewController(view, storage, i));
        } else {
            ItemViewController controller = (ItemViewController) view.getTag();
            controller.updateContent(i);
        }
        return view;
    }

    public ItemAdapter(Context context, IItemStorage storage){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.storage = storage;
    }
}
