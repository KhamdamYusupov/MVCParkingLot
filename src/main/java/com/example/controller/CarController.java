package com.example.controller;


import com.example.model.Car;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/list")
    public String getCars(Model model) {
        List<Car> list = carService.getCars();
        model.addAttribute("cars", list);
        return "carList";
    }

    @GetMapping("/search/id")
    @ResponseBody
    public Optional<Car> getCar(@PathVariable Integer id) {
        try {
            return carService.getCarById(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/registration")
    public String getCarDetails(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "registration-page";
    }

    @PostMapping("/saveCar")
    @ResponseBody
    public String registerCar(@ModelAttribute("car") Car car) {
        String result = "";
        try {
            result = carService.saveCar(car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
