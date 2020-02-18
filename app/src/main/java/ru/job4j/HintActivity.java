package ru.job4j;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class HintActivity extends AppCompatActivity {

    private final Map<Integer, String> answers = new HashMap<>();
    private Button back;
    private TextView textViewHint;
    private TextView textOfQuestion;

    public HintActivity() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity);
        back = findViewById(R.id.back);
        textOfQuestion = findViewById(R.id.text_view_question);
        textViewHint = findViewById(R.id.text_view_hint);
        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        textViewHint.setText(this.answers.get(question));

        String questionText = getIntent().getStringExtra(ExamActivity.QUESTION_TEXT);
        textOfQuestion.setText(questionText);

        back.setOnClickListener(
                view -> onBackPressed()
        );
    }
}
