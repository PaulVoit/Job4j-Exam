package ru.job4j;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.job4j.model.Option;
import ru.job4j.model.Question;

public class ExamFragment extends Fragment {
    public static final String HINT_FOR = "hint_for";
    public static final String QUESTION_TEXT = "question_text";
    public static final String RIGHT_ANSWERS = "right_answers";
    public static final String ALL_ANSWERS = "all_answers";
    private int rightAnswers = 0;
    private boolean isLastAnswerWasRight = false;
    private RadioGroup variants;
    private Button previous;
    private Button next;
    private Button hint;
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

    private final List<Integer> answers = new ArrayList<>(questions.size());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        variants = view.findViewById(R.id.variants);
        previous = view.findViewById(R.id.previous);
        next = view.findViewById(R.id.next);
        text = view.findViewById(R.id.question);
        hint = view.findViewById(R.id.hint);
        variants.setOnCheckedChangeListener(this::checkChange);
        this.fillForm();
        next.setOnClickListener(this::nextBtn);
        previous.setOnClickListener(this::previousBtn);
        hint.setOnClickListener(
                viewScreen -> {
                    Intent intent = new Intent(getActivity(), HintActivity.class);
                    intent.putExtra(HINT_FOR, position);
                    intent.putExtra(QUESTION_TEXT, this.questions.get(position).getText());
                    startActivity(intent);
                }
        );
        return view;
    }

    private void fillForm() {

        Question question = this.questions.get(this.position);
        text.setText(question.getText());

        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
            if (index == question.getUserChoice()) {
                button.setChecked(true);
            }
        }
        variants.clearCheck();
        previous.setEnabled(position != 0);
        next.setEnabled(false);


    }


    private void showAnswer() {
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(
                getActivity(), "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void saveAnswer() {
        this.answers.add(position, variants.getCheckedRadioButtonId());
    }


    private void nextBtn(View view) {

        showAnswer();
        saveAnswer();
        position++;
        if (position == questions.size()) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra(RIGHT_ANSWERS, rightAnswers);
            intent.putExtra(ALL_ANSWERS, questions.size());
            startActivity(intent);
            position--;
            rightAnswers = 0;
        } else {
            fillForm();
        }
    }

    private void previousBtn(View view) {
        int id = variants.getCheckedRadioButtonId();
        if (id == -1) {
            saveAnswer();
        }
        if (isLastAnswerWasRight) {
            rightAnswers--;
            isLastAnswerWasRight = false;
        }
        position--;
        fillForm();
    }

    private void checkChange(RadioGroup radioGroup, int i) {

        next.setEnabled(true);
    }
    public static ExamFragment of(int index) {
        ExamFragment exam = new ExamFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ExamFragment.HINT_FOR, index);
        exam.setArguments(bundle);
        return exam;
    }

}