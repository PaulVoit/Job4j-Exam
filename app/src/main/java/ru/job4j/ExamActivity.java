package ru.job4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.job4j.model.Option;
import ru.job4j.model.Question;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private RadioGroup variants;
    private Button previous;
    private Button next;
    private TextView text;

    private int position = 0;
    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    private List<Integer> answers = new ArrayList<>(questions.size());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        variants = findViewById(R.id.variants);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        text = findViewById(R.id.question);
        variants.setOnCheckedChangeListener(this::checkChange);
        this.fillForm();
        next.setOnClickListener(this::nextBtn);
        previous.setOnClickListener(this::previousBtn);

    }


    private void fillForm() {

        Question question = this.questions.get(this.position);
        text.setText(question.getText());

        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
        variants.clearCheck();
        previous.setEnabled(position != 0);
        next.setEnabled(false);


    }


    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void saveAnswer() {
        this.answers.add(position, variants.getCheckedRadioButtonId());
    }

    private void nextBtn(View view) {
        showAnswer();
        position++;
        fillForm();
    }
    private void previousBtn(View view) {
        position--;
        fillForm();
    }
    private void checkChange(RadioGroup radioGroup, int i) {
        if (position != questions.size() - 1) {
            next.setEnabled(true);
        }
    }
}
