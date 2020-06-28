package ru.job4j.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.job4j.R;
import ru.job4j.store.database.ExamBaseHelper;
import ru.job4j.store.database.ExamDbSchema;

public class ExamUpdateFragment extends Fragment {
    private SQLiteDatabase store;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_update, container, false);
        this.store = new ExamBaseHelper(this.getContext()).getWritableDatabase();
        Bundle args = getArguments();
        final EditText edit = view.findViewById(R.id.title);
        edit.setText(args.getString("name"));
        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(
                btn -> {
                    ContentValues value = new ContentValues();
                    value.put(ExamDbSchema.ExamTable.Cols.TITLE, edit.getText().toString());
                    store.update(ExamDbSchema.ExamTable.NAME, value, "id = ?", new String[]{String.valueOf(args.getInt("id"))});
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content, new ExamListFragment())
                            .addToBackStack(null)
                            .commit();
                }
        );
        return view;
    }
}
