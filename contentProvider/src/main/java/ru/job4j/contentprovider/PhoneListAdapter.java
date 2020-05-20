package ru.job4j.contentprovider;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.job4j.contentprovider.model.Store;

public class PhoneListAdapter extends RecyclerView.Adapter {
    private Store phones;


    public PhoneListAdapter(Store phones) {
        this.phones = phones;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.phone, parent, false)
        ) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView text = holder.itemView.findViewById(R.id.name);
        text.setText(phones.getItem(position));
    }

    @Override
    public int getItemCount() {
        return phones.getPhones().size();
    }

    public void setPhones(Store phones) {
        this.phones = phones;
    }

}
