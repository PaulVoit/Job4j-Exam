package ru.jo4j.backgroundthread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URL;


public class TestThread extends Thread {
    private int times;
    private static final String TAG = "BackgroundThread";
    private Handler mainHandler = new Handler();
    private static ImageView imageView;
    private ImageLoader imageLoader;

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
                final Bitmap bitmap = BitmapFactory
                        .decodeResource(imageView.getResources(), R.drawable.nastol);
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public void loadImage(String[] urls) {
        ReferenceQueue rq = new ReferenceQueue();
        imageLoader = new ImageLoader();
        imageLoader.doInBackground(urls);
        WeakReference weakReference = new WeakReference(imageLoader, rq);
    }

    private static class ImageLoader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(urls[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public void disposeImageLoader() {
        imageLoader = null;
    }
}

