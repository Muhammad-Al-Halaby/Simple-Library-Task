package library.data;

import library.pojo.Book;
import library.pojo.LibraryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDataAccess implements DatabaseAccessModel{
    private String entityName = "Book";

    @Override
    public ArrayList<Book> getAll() {
        String query = QueryBuilder.buildGetAllQuery(entityName);
        Connection connection = DBConnection.getConnection();

        ArrayList<Book> ret = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Book record = new Book(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getInt(4)
                        ,resultSet.getString(5)
                        ,resultSet.getString(6)
                        ,resultSet.getInt(7)
                        ,resultSet.getInt(8)
                        ,resultSet.getInt(9));

                ret.add(record);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    @Override
    public Book getById(int id) {
        String query = QueryBuilder.buildGetByIdQuery(entityName, id);
        Connection connection = DBConnection.getConnection();

        Book ret = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ret = new Book(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getInt(4)
                        ,resultSet.getString(5)
                        ,resultSet.getString(6)
                        ,resultSet.getInt(7)
                        ,resultSet.getInt(8)
                        ,resultSet.getInt(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    @Override
    public void delete(int id) {
        String query = QueryBuilder.buildDeleteQuery(entityName, id);
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void insert(LibraryEntity record) {
        Book book = (Book)record;
        String query = QueryBuilder.buildInsertQuery(entityName, book.getBook_id(), book.getBook_isbn(), book.getBook_title(), book.getBook_number_of_pages(), book.getBook_category(), book.getBook_language(), book.getPublication_year(), book.getAuthor_id(), book.getPublisher_id());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, LibraryEntity record) {
        Book book = (Book)record;
        String query = QueryBuilder.buildUpdateQuery(entityName, id, book.getBook_isbn(), book.getBook_title(), book.getBook_number_of_pages(), book.getBook_category(), book.getBook_language(), book.getPublication_year(), book.getAuthor_id(), book.getPublisher_id());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}