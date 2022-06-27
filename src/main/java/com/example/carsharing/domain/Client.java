package com.example.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  private int id;
  private String firstName;
  private String lastName;
  private String phone;

}
