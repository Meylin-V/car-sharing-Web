package com.example.carsharing.beans;

import com.example.carsharing.domain.Car;
import java.util.List;
import lombok.Data;

@Data
public class CarBean {

  private Car current;
  private String message;
  private List<Car> cars;
}
