package com.eystudio.android.listapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by daneel on 28.10.17.
 */

public class RemoveConfirmDialog extends DialogFragment implements DialogInterface.OnClickListener{

    public static final String RCODE_KEY =
            "com.eystudio.android.listapplication.removeconfirm.rcode";

    public static final String ITEM_KEY = "com.eystudio.android.listapplication.removeconfirm.item";
    public static final String RET_ITEM_KEY =
            "com.eystudio.android.listapplication.removeconfirm.ret_item";

    private Item item;

    private void remove(){
        ItemListActivity activity = (ItemListActivity) getActivity();
        activity.onRemoveConfirmed(item);
    }

    private void cancel(){

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_POSITIVE: remove(); break;
            case DialogInterface.BUTTON_NEGATIVE: cancel(); break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null)
            item = (Item) args.getSerializable(ITEM_KEY);

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle(R.string.conf_rem_title);
        adb.setPositiveButton(R.string.conf_rem_remove, this);
        adb.setNegativeButton(R.string.conf_rem_cancel, this);
        return adb.create();
    }

    public static RemoveConfirmDialog newInstance(Item item){
        Bundle args = new Bundle();
        args.putSerializable(ITEM_KEY, item);

        RemoveConfirmDialog dialog = new RemoveConfirmDialog();
        dialog.setArguments(args);
        return  dialog;
    }
}
