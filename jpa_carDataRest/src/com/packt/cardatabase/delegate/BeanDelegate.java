package com.packt.cardatabase.delegate;

import com.packt.cardatabase.domain.Car;

public interface BeanDelegate {
	public Car addCar(Car car);
	public void deleteCar(long id);
	public Iterable<Car> getCars();
	public Car getCar(long id);
	public void updateCar(Car car);
}
