package com.packt.cardatabase.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.packt.cardatabase.delegate.BeanDelegateImp;
import com.packt.cardatabase.domain.Car;

//Diego Garcia
@SpringBootTest
public class DelegateBeanTests {

	@Mock
	RestTemplate template;
	@InjectMocks
	BeanDelegateImp delegate;
	
	@Autowired	
	public DelegateBeanTests() {
		delegate = new BeanDelegateImp(template);
	}

	@Test
	public void addCarTest() {
		Car car = new Car();
		car.setBrand("Ford");
		car.setModel("Explorer");
		car.setColor("yellow");
		when(template.postForObject(BeanDelegateImp.WEB_PATH, car, Car.class)).thenReturn(car);
		assertEquals(delegate.addCar(car).getBrand(),"Ford");	
		verify(template).postForObject(BeanDelegateImp.WEB_PATH, car, Car.class);
		
	}
	
	@Test
	public void deleteCarTest() {	
		long id = 1;
		delegate.deleteCar(id);
		verify(template).delete(BeanDelegateImp.WEB_PATH+id);
	}
	
	@Test
	public void getCarsTest() {
		Car[] lista= new Car[2];
		when(template.getForObject(BeanDelegateImp.WEB_PATH, Car[].class)).thenReturn(lista);
		assertNotNull(delegate.getCars());
		verify(template).getForObject(BeanDelegateImp.WEB_PATH, Car[].class);		
	}
	
	@Test
	public void getCarTest() {
		long id = 1;
		Car car = new Car();
		car.setBrand("Ford");
		car.setModel("Explorer");
		car.setColor("yellow");
		when(template.getForObject(BeanDelegateImp.WEB_PATH+id, Car.class)).thenReturn(car);
		assertEquals(delegate.getCar(id).getModel(),"Explorer");
		verify(template).getForObject(BeanDelegateImp.WEB_PATH+id, Car.class);
	}
	
	@Test
	public void updateCarTest() {
		Car car = new Car();
		car.setBrand("Ford");
		car.setModel("Explorer");
		car.setColor("yellow");
		delegate.updateCar(car);
		verify(template).put(BeanDelegateImp.WEB_PATH,car);
	}
	
}
