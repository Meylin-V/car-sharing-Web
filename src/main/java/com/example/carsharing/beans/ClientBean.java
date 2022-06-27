package com.example.carsharing.beans;

import com.example.carsharing.domain.Client;
import java.util.List;
import lombok.Data;

@Data
public class ClientBean {

  private Client current;
  private String message;
  private List<Client> clients;
}
