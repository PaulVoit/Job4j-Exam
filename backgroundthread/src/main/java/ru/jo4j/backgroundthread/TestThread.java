package ru.jo4j.backgroundthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;


public class TestThread extends Thread {
    private int times;
    private static final String TAG = "BackgroundThread";
    private Handler mainHandler = new Handler();
    private ImageView imageView;

    public TestThread(int times, ImageView imageView) {
        this.times = times;
        this.imageView = imageView;
    }

    @Override
    public void run() {
        int count = 0;
        loadImageFromNetwork(imageView);
        while (count != 10) {
            Log.d(TAG, "startThread" + count);
            count++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadImageFromNetwork(final ImageView imageView) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                Picasso.get().load("https://uranote.ru/wp-content/uploads/2018/04/https1-e1524122703248.png").into(imageView);
            }
        });
    }
}
