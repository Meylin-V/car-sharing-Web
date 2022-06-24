package com.example.carsharing;

import com.example.carsharing.beans.CarBean;
import com.example.carsharing.beans.ClientBean;
import com.example.carsharing.beans.OwnerBean;
import com.example.carsharing.db.DBConnection;
import com.example.carsharing.domain.Car;
import com.example.carsharing.domain.Client;
import com.example.carsharing.domain.Owner;
import com.example.carsharing.service.CarService;
import com.example.carsharing.service.ClientService;
import com.example.carsharing.service.OwnerService;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "carSharing", value = {"/client-func", "/owner-func",
    "/add-owner", "/finish_add", "/all-cars", "/rent-car", "/add-car",
    "/finish_add_car", "/finish_client"})
public class HelloServlet extends HttpServlet {

  private int indexOwner;
  private CarService carService;
  private OwnerService ownerService;
  private ClientService clientService;

  @Resource(name = "jdbc/car_sharing")
  private DataSource ds;

  public void init() {
    carService = new CarService();
    ownerService = new OwnerService();
    clientService = new ClientService();
    try {
      DBConnection.getInstance().initConnection(ds);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    if (request.getRequestURI().contains("/owner-func")) {
      showOwners(request, response);
    } else if (request.getRequestURI().contains("/client-func")) {
      showAvailableCars(request, response);
    } else if (request.getRequestURI().contains("/add-owner")) {
      openOwnerPage(request, response);
    } else if (request.getRequestURI().contains("/all-cars")) {
      showAllCars(request, response);
    } else if (request.getRequestURI().contains("/add-car")) {
      openCarPage(request, response);
    } else if (request.getRequestURI().contains("/rent-car")) {
      openClientPage(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getRequestURI().contains("/finish_add")) {
      addNewOwner(request, response);
    } else if (request.getRequestURI().contains("/finish_client")) {
      rentCar(request, response);
    } else if (request.getRequestURI().contains("/finish_add_car")) {
      addNewCar(request, response);
    }
  }

  private void addNewCar(HttpServletRequest request, HttpServletResponse response) {
    CarBean carBean = new CarBean();
    try {
      int indexOwner = Integer.parseInt(request.getParameter("id"));
      String model = request.getParameter("model");
      String price = request.getParameter("price");
      carService.saveToDB(model, price, indexOwner);
    } catch (Exception e) {
      carBean.setMessage("error input data");
    }
    showOwners(request, response);
  }

  private void addNewOwner(HttpServletRequest request, HttpServletResponse response) {
    OwnerBean ownerBean = new OwnerBean();
    try {
      String firstName = request.getParameter("first_name");
      String lastName = request.getParameter("last_name");
      String phone = request.getParameter("phone");
      ownerService.saveToDB(firstName, lastName, phone);
    } catch (Exception e) {
      ownerBean.setMessage("error input data");
    }
    showOwners(request, response);
  }

  private void rentCar(HttpServletRequest request, HttpServletResponse response) {
    ClientBean clientBean = new ClientBean();
    try {
      String firstName = request.getParameter("first_name");
      String lastName = request.getParameter("last_name");
      String phone = request.getParameter("phone");
      clientService.saveToDB(firstName, lastName, phone);

      int clientIndex = clientService.getIndex();
      int carIndex = Integer.parseInt(request.getParameter("id"));

      carService.makeNotAvailable(carIndex, clientIndex);
    } catch (Exception e) {
      clientBean.setMessage("error input data");
    }
    showAvailableCars(request, response);
  }

  private void openClientPage(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    ClientBean clientBean = new ClientBean();
    request.setAttribute("clientBean", clientBean);
    request.getRequestDispatcher("/new-client.jsp").forward(request, response);
  }

  private void openOwnerPage(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    OwnerBean ownerBean = new OwnerBean();
    request.setAttribute("ownerBean", ownerBean);
    request.getRequestDispatcher("/new-owner.jsp").forward(request, response);
  }

  private void showOwners(HttpServletRequest request, HttpServletResponse response) {
    OwnerBean ownerBean = new OwnerBean();
    try {
      List<Owner> ownerList = ownerService.findAll();
      ownerBean.setOwners(ownerList);
      request.setAttribute("ownerBean", ownerBean);
      request.getRequestDispatcher("/view-owners.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void showAvailableCars(HttpServletRequest request, HttpServletResponse response) {
    CarBean carBean = new CarBean();
    try {
      List<Car> carList = carService.findAvailable();
      carBean.setCars(carList);
      request.setAttribute("carBean", carBean);
      request.getRequestDispatcher("/view-cars-av.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void showAllCars(HttpServletRequest request, HttpServletResponse response) {
    CarBean carBean = new CarBean();
    try {
      List<Car> carList = carService.findAll();
      carBean.setCars(carList);
      request.setAttribute("carBean", carBean);
      request.getRequestDispatcher("/view-cars-all.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void openCarPage(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    CarBean carBean = new CarBean();
    request.setAttribute("carBean", carBean);
    request.getRequestDispatcher("/new-car.jsp").forward(request, response);
  }

  public void destroy() {
  }
}