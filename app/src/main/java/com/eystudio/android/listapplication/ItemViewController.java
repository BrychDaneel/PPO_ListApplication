package com.eystudio.android.listapplication;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by daneel on 27.10.17.
 */

public class ItemViewController implements View.OnTouchListener{
    private View view;
    private int position;
    private IItemStorage storage;
    private IImageSource imageSource;

    private TextView name;
    private ImageView picture;
    private LinearLayout mainView;
    private LinearLayout controlView;

    private float controlPercent;
    private final float CONTROL_STABLE = 0.333f;

    private void getElements(){
        name = view.findViewById(R.id.item_name);
        picture = view.findViewById(R.id.picture);
        mainView = view.findViewById(R.id.main_item_content);
        controlView = view.findViewById(R.id.controll_item_content);
    }

    private void bind(){
        view.setOnTouchListener(this);
    }

    public void updateContent(int position){
        Item item = storage.getItem(position);
        name.setText(item.getName());
        int imageId = imageSource.getImageId(item.getImage());
        picture.setImageResource(imageId);
    }

    public ItemViewController(int position, View view,
                              IItemStorage storage, IImageSource imageSource) {
        this.view = view;
        this.position = position;
        this.storage = storage;
        this.imageSource = imageSource;
        getElements();
        bind();
        updateContent(position);
    }

    private void updateControlPersent(){
        LinearLayout.LayoutParams params;

        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1 - controlPercent);
        mainView.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                controlPercent);
        controlView.setLayoutParams(params);
    }

    private float last_x;
    private boolean pressed;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            last_x = motionEvent.getX();
            pressed = true;
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP ||
                motionEvent.getAction() == MotionEvent.ACTION_CANCEL){
            pressed = false;
            if (controlPercent < CONTROL_STABLE){
                controlPercent = 0;
                updateControlPersent();
            }
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE && pressed){
            float dx = motionEvent.getX() - last_x;
            last_x = motionEvent.getX();
            float width = view.getWidth();
            float pdx = dx / width;
            controlPercent -= pdx;
            if (controlPercent < 0)
                controlPercent = 0;
            if (controlPercent > CONTROL_STABLE)
                controlPercent = CONTROL_STABLE;
            updateControlPersent();
        }

        return true;
    }
}
