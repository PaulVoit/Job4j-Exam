package ru.job4j;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class DialogActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener,
        TimePickerFragment.TimePickerListener {

    private String selectedTime = "";
    private String selectedDate = "";
    private TextView dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        dateTime = findViewById(R.id.dateTime);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDate = String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, month, year);
        setDateTime();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        selectedTime = String.format(Locale.getDefault(), " %02d:%02d", hourOfDay, minute);
        setDateTime();
    }

    private void setDateTime() {
        dateTime.setText(String.format("%s%s", selectedDate, selectedTime));
    }

    public void OnClickDate(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "dateFragment");
    }

    public void OnClickTime(View view) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "timeFragment");
    }

}
