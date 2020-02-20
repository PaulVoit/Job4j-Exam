package ru.job4j;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.job4j.model.Option;
import ru.job4j.model.Question;

public class ExamActivity extends BaseActivity {

    public Fragment loadFrg() {
        return new ExamFragment();
    }
}
