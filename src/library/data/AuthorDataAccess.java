package library.data;

import library.pojo.Author;
import library.pojo.LibraryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDataAccess implements DatabaseAccessModel{
    private String entityName = "Author";

    @Override
    public ArrayList<Author> getAll() {
        String query = QueryBuilder.buildGetAllQuery(entityName);
        Connection connection = DBConnection.getConnection();

        ArrayList<Author> ret = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Author record = new Author(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3));

                ret.add(record);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    @Override
    public Author getById(int id) {
        String query = QueryBuilder.buildGetByIdQuery(entityName, id);
        Connection connection = DBConnection.getConnection();

        Author ret = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ret = new Author(resultSet.getInt(1)
                        , resultSet.getString(2)
                        , resultSet.getString(3));
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
        Author author = (Author)record;
        String query = QueryBuilder.buildInsertQuery(entityName, author.getAuthor_id(), author.getAuthor_full_name(), author.getAuthor_biography());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, LibraryEntity record) {
        Author author = (Author)record;
        String query = QueryBuilder.buildUpdateQuery(entityName, id, author.getAuthor_full_name(), author.getAuthor_biography());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
