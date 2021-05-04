package library.data;

import library.pojo.LibraryEntity;

import java.util.ArrayList;

interface DatabaseAccessModel<T extends LibraryEntity> {
    ArrayList<T> getAll();

    T getById(int id);

    void delete(int id);

    void insert(T record);

    void update(int id, T record);
}
