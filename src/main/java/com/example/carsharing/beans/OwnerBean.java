package com.example.carsharing.beans;

import com.example.carsharing.domain.Owner;
import java.util.List;
import lombok.Data;

@Data
public class OwnerBean {
private Owner current;
private String message;
private List<Owner> owners;
}
