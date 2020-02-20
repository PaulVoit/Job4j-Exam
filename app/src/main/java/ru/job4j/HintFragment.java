package ru.job4j;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class HintFragment extends Fragment {
    private final Map<Integer, String> answers = new HashMap<>();
    private Button back;
    private TextView textViewHint;
    private TextView textOfQuestion;

    public HintFragment() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hint_fragment, container, false);
        back = view.findViewById(R.id.back);
        textOfQuestion = view.findViewById(R.id.text_view_question);
        textViewHint = view.findViewById(R.id.text_view_hint);
        int question = getActivity().getIntent().getIntExtra(ExamFragment.HINT_FOR, 0);
        textViewHint.setText(this.answers.get(question));

        String questionText = getActivity().getIntent().getStringExtra(ExamFragment.QUESTION_TEXT);
        textOfQuestion.setText(questionText);

        back.setOnClickListener(
                viewScreen -> getActivity().onBackPressed()
        );
        return view;
    }
}
