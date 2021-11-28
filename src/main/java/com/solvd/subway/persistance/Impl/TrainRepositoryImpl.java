package com.solvd.subway.persistance.Impl;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;
import com.solvd.subway.domain.exception.DeleteDataException;
import com.solvd.subway.domain.exception.InsertDataException;
import com.solvd.subway.domain.exception.UpdateDataException;
import com.solvd.subway.persistance.ConnectionPool;
import com.solvd.subway.persistance.TrainRepository;

import java.sql.*;

public class TrainRepositoryImpl implements TrainRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public void create(Train train, Subway subway) {
        Connection connection = CONNECTION_POOL.getConnection();
        String insert = "Insert into trains(subway_id, model, speed, creation_date) values (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, subway.getId());
            preparedStatement.setString(2, train.getModel());
            preparedStatement.setInt(3, train.getSpeed());
            preparedStatement.setDate(4, Date.valueOf(train.getCreationDate()));

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                train.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertDataException("Cannot insert train to a DataBase", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update() {
        Connection connection = CONNECTION_POOL.getConnection();
        String update = "Update trains set model = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)){
            Long id = 3L;
            String model = "NewModel";

            preparedStatement.setString(1, model);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateDataException("Cannot update train data", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete() {
        Connection connection = CONNECTION_POOL.getConnection();
        String delete = "Delete from trains where speed < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)){
            Integer speed = 100;
            preparedStatement.setInt(1, speed);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteDataException("Cannot delete train data", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}