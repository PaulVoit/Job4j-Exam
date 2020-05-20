package ru.job4j.contentprovider.model;

import java.util.ArrayList;
import java.util.List;

public class PhoneStore implements Store {
    private List<String> phones;

    public PhoneStore() {
        this.phones = new ArrayList<>();
    }

    @Override
    public void addPhone(String phone) {
        phones.add(phone);
    }

    @Override
    public List<String> getPhones() {
        return phones;
    }

    @Override
    public void clear() {
        phones.clear();
    }

    @Override
    public String getItem(int position) {
        return phones.get(position);
    }
}
