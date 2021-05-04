package library.data;

import library.pojo.LibraryEntity;

import java.sql.Connection;

interface DatabaseAccessModel<T extends LibraryEntity> {
    default void insert(T obj){
        Connection connection = DBConnection.getConnection();
        
    };
    void delete(int id);
}
