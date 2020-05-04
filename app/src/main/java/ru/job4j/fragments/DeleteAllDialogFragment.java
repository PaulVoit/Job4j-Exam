package ru.job4j.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DeleteAllDialogFragment extends DialogFragment {

    private DeleteAllDialogListener callback;

    public DeleteAllDialogFragment(DeleteAllDialogListener callback) {
        this.callback = callback;
    }

    public interface DeleteAllDialogListener {

        void onPositiveDialogClick(DialogFragment dialog);

        void onNegativeDialogClick(DialogFragment dialog);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("Удалить все?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(DeleteAllDialogFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onNegativeDialogClick(DeleteAllDialogFragment.this);
                    }
                })
                .create();
        return dialog;
    }

    @Override

    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
