package ru.job4j;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ExamFragment;
import ru.job4j.fragments.HintFragment;

public class HintActivity extends BaseActivity {

    public Fragment loadFrg() {
        return HintFragment.of(
                getIntent().getIntExtra(ExamFragment.HINT_FOR, 0)
        );
    }
}
