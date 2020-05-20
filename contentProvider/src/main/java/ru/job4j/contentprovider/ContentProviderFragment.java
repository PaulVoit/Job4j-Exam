package ru.job4j.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.contentprovider.model.PhoneStore;
import ru.job4j.contentprovider.model.Store;

public class ContentProviderFragment extends Fragment implements SearchView.OnQueryTextListener {

    public static final String PHONE = "phone";
    private PhoneListAdapter adapter;
    private Store phones;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recycler = view.findViewById(R.id.phones);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        phones = new PhoneStore();
        adapter = new PhoneListAdapter(phones);
        recycler.setAdapter(adapter);
        loadDic(phones.getPhones());
        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        return view;
    }

    private void loadDic(List<String> phones) {
        Cursor cursor = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                null,
                null, null);
        updateAdapter(cursor);
    }

    private void findContact(String by) {
        Cursor cursor = getActivity().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like '%" + by + "%'",
                null,
                null);
        updateAdapter(cursor);
    }

    private void updateAdapter(Cursor cursor) {
        try {
            phones.clear();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.addPhone(name + " " + phone);
            }
            adapter.notifyDataSetChanged();
        } finally {
            cursor.close();
        }
    }

    public static ContentProviderFragment of(int index) {
        ContentProviderFragment fragment = new ContentProviderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("phone", index);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("tag", "onQueryTextChange: " + newText);

        if (newText.length() > 2)
            findContact(newText);
        return false;
    }
}