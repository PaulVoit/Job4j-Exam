package ru.job4j.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.job4j.R;
import ru.job4j.fragments.ExamFragment;

public class ResultActivity extends AppCompatActivity {

    TextView textViewRightAnswers;
    TextView textViewAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViewRightAnswers = findViewById(R.id.text_view_right_answers);
        textViewAnswers = findViewById(R.id.text_view_answers);

        Intent intent = getIntent();

        textViewAnswers.setText("Quantity of questions: " + intent.getIntExtra(ExamFragment.ALL_ANSWERS, 0));
        textViewRightAnswers.setText("Right answers: " + intent.getIntExtra(ExamFragment.RIGHT_ANSWERS, 0));


    }
}
