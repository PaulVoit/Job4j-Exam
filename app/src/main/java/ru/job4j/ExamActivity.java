package ru.job4j;

import androidx.fragment.app.Fragment;

public class ExamActivity extends BaseActivity {

    public Fragment loadFrg() {
        return ExamFragment.of(
                getIntent().getIntExtra(ExamFragment.HINT_FOR, 0)
        );
    }
}
