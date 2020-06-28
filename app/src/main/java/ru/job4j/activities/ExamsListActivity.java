package ru.job4j.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.activities.BaseActivity;
import ru.job4j.activities.DialogActivity;
import ru.job4j.R;
import ru.job4j.exam.store.Exam;
import ru.job4j.fragments.DeleteAllDialogFragment;
import ru.job4j.fragments.ExamListFragment;


public class ExamsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.host_frg);
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .add(R.id.content, loadFrg())
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return ExamListFragment.of(
                getIntent().getIntExtra(ExamListFragment.LIST_OF, 0)
        );
    }
}