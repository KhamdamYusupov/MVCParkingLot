package com.example.service;

import com.example.model.Car;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    @Override
    public List<Car> getCars() {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cars", "postgres", "Pshtqapipi0209");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int horsepower = resultSet.getInt("horsepower");

                Car obj = new Car();
                obj.setName(name);
                obj.setPrice(price);
                obj.setHorsePower(horsepower);

                carList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return carList;
    }

    @Override
    public String saveCar(Car car) throws IOException {
        String name = car.getName();
        Integer price = car.getPrice();
        Integer horsePower = car.getHorsePower();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cars", "postgres", "Pshtqapipi0209");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cars (name, price, horsepower) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, horsePower);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL state: %s \n %s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Your car has successfully registered!!!" + "If you want to see the list of all registered cars, go to :" + "<a href=\"/cars/list\">List of all the registered cars</a>";
    }

    @Override
    public String getCarByName(String name) throws IOException {
        boolean found = false;
        String foundMessage = "";
        String notFoundMessage = "";

        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/cars", "postgres", "Pshtqapipi0209");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars WHERE name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                found = resultSet.getString("name").equalsIgnoreCase(name);
            }
            foundMessage = "The searched car " + name + " exists in the database";
            notFoundMessage = "The searched car " + name + " does NOT exist in the database";


        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (found) {
            return foundMessage;
        } else {
            return notFoundMessage;
        }
    }


}

