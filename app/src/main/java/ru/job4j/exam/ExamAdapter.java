package ru.job4j.exam;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import ru.job4j.ExamActivity;
import ru.job4j.R;
import ru.job4j.exam.store.Exam;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> implements View.OnClickListener {

    private final List<Exam> exams;


    ExamAdapter(List<Exam> exams) {
        this.exams = exams;
    }

    public class ExamHolder extends RecyclerView.ViewHolder {

        private View view;

        public ExamHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
        }
    }

    @NonNull
    @Override
    public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.info_exam, parent, false);
        return new ExamHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExamHolder holder, int i) {
        final Exam exam = this.exams.get(i);
        TextView text = holder.view.findViewById(R.id.info);
        TextView result = holder.view.findViewById(R.id.result);
        TextView date = holder.view.findViewById(R.id.date);
        text.setText(exam.getName());
        result.setText("" + exam.getResult());
        date.setText("" + new Date(exam.getTime()));
        text.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return this.exams.size();
    }


    @Override
    public void onClick(View v) {
        v.getContext().startActivity(new Intent(v.getContext(), ExamActivity.class));
    }
}
