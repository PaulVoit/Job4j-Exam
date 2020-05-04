package ru.job4j;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.GreetFragment;

public class GreetActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new GreetFragment();
    }
}
