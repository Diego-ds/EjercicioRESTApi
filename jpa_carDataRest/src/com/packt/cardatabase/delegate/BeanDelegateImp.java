package com.packt.cardatabase.delegate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.packt.cardatabase.domain.Car;

//Diego Garcia
@Component
public class BeanDelegateImp implements BeanDelegate{
	
	
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/cars/";
	
	@Autowired
	public BeanDelegateImp(RestTemplate template) {
		this.template = template;
	}
	
	@Override
	public Car addCar(Car car) {
        return template.postForObject(WEB_PATH, car, Car.class);       
	}
	
	@Override
	public void deleteCar(long id) {		
        template.delete(WEB_PATH+id); 
	}
	@Override
	public Iterable<Car> getCars() {
		Car[] cars = template.getForObject(WEB_PATH,Car[].class);
		return Arrays.asList(cars);
	}
	@Override
	public Car getCar(long id){
		Car car = template.getForObject(WEB_PATH+id, Car.class);
		System.out.println(car.toString());
		return car;
	}
	@Override
	public void updateCar(Car car) {
		template.put(WEB_PATH, car);
	}
	
}
