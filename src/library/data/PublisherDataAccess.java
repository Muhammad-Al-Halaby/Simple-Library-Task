package library.data;

import library.pojo.LibraryEntity;
import library.pojo.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublisherDataAccess implements DatabaseAccessModel{
    private String entityName = "Publisher";

    @Override
    public ArrayList<Publisher> getAll() {
        String query = QueryBuilder.buildGetAllQuery(entityName);
        Connection connection = DBConnection.getConnection();

        ArrayList<Publisher> ret = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Publisher record = new Publisher(resultSet.getInt(1),
                        resultSet.getString(2));

                ret.add(record);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ret;
    }

    @Override
    public Publisher getById(int id) {
        String query = QueryBuilder.buildGetByIdQuery(entityName, id);
        Connection connection = DBConnection.getConnection();

        Publisher ret = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ret = new Publisher(resultSet.getInt(1),
                        resultSet.getString(2));
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
        Publisher publisher = (Publisher)record;
        String query = QueryBuilder.buildInsertQuery(entityName, publisher.getPublisher_id(), publisher.getPublisher_name());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, LibraryEntity record) {
        Publisher publisher = (Publisher)record;
        String query = QueryBuilder.buildUpdateQuery(entityName, id, publisher.getPublisher_name());
        Connection connection = DBConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
