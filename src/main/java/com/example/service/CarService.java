package com.example.service;

import com.example.model.Car;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface CarService {

    List<Car> getCars();

    String saveCar(Car car) throws IOException;

    Optional<Car>getCarById(Integer id) throws IOException;
}
