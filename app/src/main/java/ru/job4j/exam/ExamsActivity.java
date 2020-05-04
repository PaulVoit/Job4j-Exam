package ru.job4j.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.DialogActivity;
import ru.job4j.R;
import ru.job4j.exam.store.Exam;
import ru.job4j.fragments.DeleteAllDialogFragment;


public class ExamsActivity extends AppCompatActivity implements DeleteAllDialogFragment.DeleteAllDialogListener {
    private RecyclerView recycler;
    List<Exam> exams = new ArrayList<Exam>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(ExamsActivity.this, "ADD", Toast.LENGTH_SHORT).show();
                exams.add(new Exam(exams.size(),String.format("Exam %s", exams.size()), System.currentTimeMillis(), exams.size()));
                this.recycler.getAdapter().notifyDataSetChanged();
                return true;
            case R.id.delete_item:
                Toast.makeText(ExamsActivity.this, "DELETE", Toast.LENGTH_SHORT).show();
                DialogFragment dialog = new DeleteAllDialogFragment(ExamsActivity.this);
                dialog.show(getSupportFragmentManager(), "delete_tag");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateUI();
    }

    private void updateUI() {
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), index));
        }
        this.recycler.setAdapter(new ExamAdapter(exams));
    }

    public void onClickDateTimeFragment(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        exams.clear();
        this.recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {

    }
}