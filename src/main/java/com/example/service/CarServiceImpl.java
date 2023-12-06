package com.example.service;

import com.example.model.Car;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {
    private final JdbcTemplate jdbcTemplate;

    RowMapper<Car> rowMapper = (rs, rowNum) -> {
        Car car = new Car();
        car.setName(rs.getString("name"));
        car.setPrice(rs.getInt("price"));
        car.setHorsePower(rs.getInt("horsePower"));
        return car;
    };

    public CarServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getCars() {
        String SQL_LIST = "select * from cars";
        return jdbcTemplate.query(SQL_LIST, rowMapper);
    }

    @Override
    public String saveCar(Car car) throws IOException {
        String SQL_SAVE = "insert into cars(name, price, horsePower) values(?, ?, ?)";
        final int updatedRows = jdbcTemplate.update(SQL_SAVE, car.getName(), car.getPrice(), car.getHorsePower());
        if (updatedRows > 0) {
            return "Your car has successfully registered!!!" + "If you want to see the list of all registered cars, go to :" + "<a href=\"/cars/list\">List of all the registered cars</a>";
        } else {
            throw new DataRetrievalFailureException("Couldn't retrieve the required data");
        }
    }


    @Override
    public Optional<Car> getCarById(Integer id) throws IOException {
        String SQL_FIND_BY_NAME = "SELECT * FROM cars WHERE id = ?";
        Car car = null;
        try {
            car = jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, rowMapper, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
      return Optional.ofNullable(car);
    }
}

