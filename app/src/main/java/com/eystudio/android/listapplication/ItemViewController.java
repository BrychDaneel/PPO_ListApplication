package com.eystudio.android.listapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by daneel on 27.10.17.
 */

public class ItemViewController implements View.OnTouchListener, View.OnClickListener{
    private View view;
    private int position;
    private IItemStorage storage;
    private IImageSource imageSource;

    private TextView name;
    private ImageView picture;
    private LinearLayout mainView;
    private LinearLayout controlView;
    private BaseAdapter adapter;
    private ImageButton editButton;
    private ImageButton deleteButton;

    private float controlPercent;
    private final float CONTROL_STABLE = 0.333f;
    private final String DIALOG_REMOVE_CONFIGM = "RemoveConfirm";

    private void getElements(){
        name = view.findViewById(R.id.item_name);
        picture = view.findViewById(R.id.picture);
        mainView = view.findViewById(R.id.main_item_content);
        controlView = view.findViewById(R.id.control_item_content);
        editButton = view.findViewById(R.id.button_edit);
        deleteButton = view.findViewById(R.id.button_delete);
    }

    private void bind(){
        view.setOnTouchListener(this);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    public void updateContent(int position){
        this.position = position;
        Item item = storage.getItem(position);
        name.setText(item.getName());
        int imageId = imageSource.getImageId(item.getImage());
        picture.setImageResource(imageId);
        controlPercent = 0;
        updateControlPercent();
    }

    public ItemViewController(int position, View view, BaseAdapter adapter,
                              IItemStorage storage, IImageSource imageSource) {
        this.view = view;
        this.adapter = adapter;
        this.position = position;
        this.storage = storage;
        this.imageSource = imageSource;
        getElements();
        bind();
        updateContent(position);
    }

    private void updateControlPercent(){
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
                updateControlPercent();
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
            updateControlPercent();
        }

        return true;
    }

    private void onDelete(){
        Activity activity = (Activity) view.getContext();
        FragmentManager fm = activity.getFragmentManager();
        RemoveConfirmDialog dialog = RemoveConfirmDialog.newInstance(storage.getItem(position));
        dialog.show(fm, DIALOG_REMOVE_CONFIGM);
        //storage.deleteItem(storage.getItem(position));
        //adapter.notifyDataSetChanged();
    }

    private void onEdit(){
        Activity activity = (Activity) view.getContext();
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra(EditActivity.RCODE_KEY, EditActivity.EDIT_ITEM_RCODE);
        intent.putExtra(EditActivity.ITEM_KEY, storage.getItem(position));
        activity.startActivityForResult(intent, EditActivity.EDIT_ITEM_RCODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_delete: onDelete(); break;
            case R.id.button_edit: onEdit(); break;
        }
    }

}
