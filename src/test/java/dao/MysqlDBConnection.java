package dao;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDBConnection {

        private MysqlParameters parameters;
        private MysqlDataSource dataSource;

    public MysqlDBConnection() {
        prepareParameters();
    }


        private void prepareParameters() {
            // załadowanie z zasobów
            try {
                parameters = MysqlParameters.loadFromResources();
            } catch (IOException e) {
                System.err.println("Błąd : " + e.getMessage());
                System.exit(9);
            }

            dataSource = new MysqlDataSource();
            dataSource.setUser(parameters.getUsername());
            dataSource.setPassword(parameters.getPassword());
            dataSource.setDatabaseName(parameters.getDbName());
            dataSource.setServerName(parameters.getHost());
            dataSource.setPort(parameters.getPort());

            try {
                dataSource.setCreateDatabaseIfNotExist(true);
                dataSource.setServerTimezone("Europe/Warsaw");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
    }


