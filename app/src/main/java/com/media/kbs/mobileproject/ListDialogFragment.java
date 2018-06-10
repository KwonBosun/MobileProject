package com.media.kbs.mobileproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class ListDialogFragment extends DialogFragment {
    private String Item = "데이터베이스에서 받아오기";
    final CharSequence[] choice = {"수정", "삭제"};


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(Item)
                .setItems(choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                //수정
                                Toast.makeText(getActivity(), choice[0], Toast.LENGTH_SHORT).show();

                            case 1:
                                //수정
                                Toast.makeText(getActivity(), choice[1], Toast.LENGTH_SHORT).show();
                        }
                        dismiss();
                    }
                });
        return builder.create();
    }
}
