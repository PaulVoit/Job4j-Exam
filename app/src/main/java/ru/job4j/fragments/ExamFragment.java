package ru.job4j.fragments;

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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.activities.HintActivity;
import ru.job4j.R;
import ru.job4j.activities.ResultActivity;
import ru.job4j.activities.ExamsListActivity;
import ru.job4j.store.Option;
import ru.job4j.store.Question;
import ru.job4j.store.QuestionStore;

public class ExamFragment extends Fragment implements View.OnClickListener, ConfirmHintDialogFragment.ConfirmHintDialogListener {
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
    private Button listOfExams;
    private TextView text;
    private final QuestionStore store = QuestionStore.getInstance();
    private int position = 0;
    private final List<Integer> answers = new ArrayList<>(store.size());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        variants = view.findViewById(R.id.variants);
        previous = view.findViewById(R.id.previous);
        next = view.findViewById(R.id.next);
        text = view.findViewById(R.id.question);
        hint = view.findViewById(R.id.hint);
        listOfExams = view.findViewById(R.id.list_of_exams);
        variants.setOnCheckedChangeListener(this::checkChange);
        this.fillForm();
        next.setOnClickListener(this::nextBtn);
        previous.setOnClickListener(this::previousBtn);
        listOfExams.setOnClickListener(this::listOfExamsBtn);
        hint.setOnClickListener(this);
        return view;
    }

    private void fillForm() {
        Question question = store.get(this.position);
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
        Question question = store.get(this.position);
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
        if (position == store.size()) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra(RIGHT_ANSWERS, rightAnswers);
            intent.putExtra(ALL_ANSWERS, store.size());
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

    private void listOfExamsBtn(View view) {
        Intent intent = new Intent(getActivity(), ExamsListActivity.class);
        startActivity(intent);
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

    @Override
    public void onClick(View v) {
        DialogFragment dialog = new ConfirmHintDialogFragment(ExamFragment.this);
        dialog.show(getActivity().getSupportFragmentManager(), "dialog_tag");
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        Intent intent = new Intent(getActivity(), HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        intent.putExtra(QUESTION_TEXT, store.get(position).getText());
        startActivity(intent);
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        Toast.makeText(getActivity(), "Молодец!!!", Toast.LENGTH_SHORT).show();
    }
}