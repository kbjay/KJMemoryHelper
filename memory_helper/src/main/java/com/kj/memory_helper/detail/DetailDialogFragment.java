package com.kj.memory_helper.detail;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.kj.memory_helper.R;
import com.kj.memory_helper.db.WarningMsg;

public class DetailDialogFragment extends DialogFragment {

    public static DetailDialogFragment newInstance(WarningMsg msg) {
        DetailDialogFragment fragmentOne = new DetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", msg.getType());
        bundle.putString("content", msg.getContent());
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = "";
        String content = "";
        Bundle arguments = getArguments();
        if (arguments != null) {
            title = arguments.getString("title");
            content = arguments.getString("content");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_detail_dialog, null);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.content)).setText(content);
        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}