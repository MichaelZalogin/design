package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean tmp = storage.containsKey(id);
        if (tmp) {
            storage.put(id, model);
        }
        return tmp;
    }

    @Override
    public boolean delete(String id) {
        boolean tmp = storage.containsKey(id);
        if (tmp) {
            storage.remove(id);
        }
        return tmp;
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}