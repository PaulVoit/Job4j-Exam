package ru.job4j.contentprovider.model;

import java.util.List;

public interface Store {
    void addPhone(String phone);

    List<String> getPhones();

    void clear();

    String getItem(int position);
}
