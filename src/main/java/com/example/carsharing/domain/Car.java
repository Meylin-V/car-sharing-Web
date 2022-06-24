package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

  private int id;
  private String model;
  private int price;
  private Owner owner;
  private Client client;
  private boolean available;

  public String printOwner() {
    return owner.getFirstName() + " " + owner.getLastName();
  }

  public String printOwnerPhone() {
    return owner.getPhone();
  }

  public String printClient() {
    return client == null ? "" : client.getFirstName() + " " + client.getLastName();
  }
}
