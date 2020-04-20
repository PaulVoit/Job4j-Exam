package ru.job4j;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

public class HintActivity extends BaseActivity {

    public Fragment loadFrg() {
        return HintFragment.of(
                getIntent().getIntExtra(ExamFragment.HINT_FOR, 0)
        );
    }
}
