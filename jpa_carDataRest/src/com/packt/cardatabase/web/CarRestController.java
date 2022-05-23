package com.packt.cardatabase.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.services.CarService;

@RestController
public class CarRestController {
	@Autowired
	private CarService carService;

	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return carService.getCars();
	}
	
	@PostMapping("/cars")
	public void addCar(@RequestBody Car car) {
		carService.addCar(car);
	}
	
	@GetMapping("/cars/{id}")
	public Optional<Car> getCar(@PathVariable long id){
		return carService.getCar(id);
	}
	
	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable long id) {
		Car car = carService.getCar(id).get();
		carService.deleteCar(car);
	}
	
	@PutMapping("/cars")
	public Car updateCar(@RequestBody Car car){
		return carService.updateCar(car);	
	}
	
}