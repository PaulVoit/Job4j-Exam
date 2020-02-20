package ru.job4j;

import androidx.fragment.app.Fragment;

public class GreetActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new GreetFragment();
    }
}
