package ru.job4j.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_frg);
        FragmentManager fm = getSupportFragmentManager();
        Fragment contentProviderFragment = loadFrg();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .replace(R.id.content, contentProviderFragment)
                    .commit();
        }
    }

    public Fragment loadFrg() {
        return ContentProviderFragment.of(getIntent().getIntExtra("phone", 0));
    }

}
