package ru.jo4j.backgroundthread;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Background";
    private volatile boolean stopThread = false;
    private ImageView imageView;
    private TestThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    public void startThread(View view) {
        thread = new TestThread(10, imageView);
        thread.start();
        thread.loadImage(new String[]{"storage/0/emulated/1.jpg"});
    }

    public void stopThread(View view) {
        stopThread = false;
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "stop: ");
    }

    @Override
    protected void onDestroy() {
        thread.interrupt();
        thread.disposeImageLoader();
        super.onDestroy();
    }
}