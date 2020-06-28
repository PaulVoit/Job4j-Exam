package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ExamFragment;

public class ExamActivity extends BaseActivity {
    @Override
    public Fragment loadFrg() {
        return ExamFragment.of(
                getIntent().getIntExtra(ExamFragment.HINT_FOR, 0)
        );
    }
}
