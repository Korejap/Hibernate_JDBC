package dao;

import Model.Pet;
import Model.Race;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {

    private MysqlDBConnection connector;

    public PetDao(MysqlDBConnection connection) {
        this.connector = connection;

        createDatabaseAndTable();
    }

    private void createDatabaseAndTable() {
        try {
            Connection connection = connector.createConnection();

            PreparedStatement statement = connection.prepareStatement(PetQuerries.CREATE_TABLE);
            statement.execute();

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void insertPet(Pet pet) {
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.INSERT_PET, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setString(2, pet.getOwnerName());
            preparedStatement.setInt(3, pet.getAge());
            preparedStatement.setDouble(4, pet.getWeight());
            preparedStatement.setBoolean(5, pet.isPureRace());
            preparedStatement.setString(6, pet.getRace().toString());

            int affectedRecords = preparedStatement.executeUpdate();

            System.out.println("Dodanych rekordów: " + affectedRecords);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long identifier = generatedKeys.getLong(1);
                pet.setId(identifier);

                System.out.println("Generated id: " + pet.getId());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.SELECT_PETS);

            ResultSet rekordy = preparedStatement.executeQuery();


            while (rekordy.next()) {
                Pet pet = new Pet();
                pet.setId(rekordy.getLong(1));
                pet.setName(rekordy.getString(2));
                pet.setOwnerName(rekordy.getString(3));
                pet.setAge(Integer.parseInt(rekordy.getString(4)));
                pet.setWeight(Double.parseDouble(rekordy.getString(5)));
                pet.setPureRace(Boolean.parseBoolean(rekordy.getString(5)));
                pet.setRace(Race.valueOf(rekordy.getString(6)));


                pets.add(pet);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return pets;
    }

    public void deletePet(long identifier){
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.DELETE_PET);
            preparedStatement.setLong(1, identifier);

            int affectedRecords = preparedStatement.executeUpdate();
            System.out.println("Usuniętych rekordów: " + affectedRecords);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        }

        private void updatePet (Pet pet) {
            try (Connection connection = connector.createConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(PetQuerries.DELETE_PET);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}
