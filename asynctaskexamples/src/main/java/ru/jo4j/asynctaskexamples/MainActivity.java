package ru.jo4j.asynctaskexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private Disposable sbr;
    private ProgressBar bar;
    private TextView info;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.load);
        info = findViewById(R.id.info);
        boolean recreated = bundle != null;
        final int startAt = recreated ? bundle.getInt("progress", 0) : 0;
        info.setText(startAt + "%");
        Button btnStart = findViewById(R.id.start);
        Button btnStop = findViewById(R.id.stop);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sbr == null || sbr.isDisposed()) {
                    start(startAt);
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sbr != null) {
                    sbr.dispose();
                }
            }
        });
        if (recreated) {
            start(startAt);
        }
    }

    public void start(final int startAt) {
        this.sbr = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long v) throws Exception {
                        info.setText(startAt + v.intValue() + "%");
                        bar.setProgress(startAt + v.intValue());
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("progress", bar.getProgress());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.sbr != null) {
            this.sbr.dispose();
        }
    }
}
