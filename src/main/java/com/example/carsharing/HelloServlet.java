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
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "carSharing", value = {"/client-func", "/owner-func", "/manager-func",
    "/add-owner", "/finish_add_owner", "/all-cars", "/rent-car", "/add-car",
    "/finish_add_car", "/delete-client", "/finish_client",
    "/order_id_av", "/order_price_av", "/order_id_all", "/order_price_all", "/order_av"})
public class HelloServlet extends HttpServlet {

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

  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    if (request.getRequestURI().contains("/owner-func")) {
      showOwners(request, response);
    } else if (request.getRequestURI().contains("/client-func") ||
        request.getRequestURI().contains("/order_id_av")) {
      showAvailableCars(request, response, "id");
    } else if (request.getRequestURI().contains("/order_price_av")) {
      showAvailableCars(request, response, "price");
    } else if (request.getRequestURI().contains("/add-owner")) {
      openOwnerPage(request, response);
    } else if (request.getRequestURI().contains("/manager-func")) {
      showClients(request, response);
    } else if (request.getRequestURI().contains("/all-cars")
        || request.getRequestURI().contains("/order_id_all")) {
      showAllCars(request, response, "id");
    } else if (request.getRequestURI().contains("/order_price_all")) {
      showAllCars(request, response, "price");
    } else if (request.getRequestURI().contains("/order_av")) {
      showAllCars(request, response, "av");
    } else if (request.getRequestURI().contains("/rent-car")) {
      openClientPage(request, response);
    } else if (request.getRequestURI().contains("/add-car")) {
      openCarPage(request, response);
    } else if (request.getRequestURI().contains("/delete-client")) {
      deleteClient(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    if (request.getRequestURI().contains("/finish_add_owner")) {
      addNewOwner(request, response);
    } else if (request.getRequestURI().contains("/finish_client")) {
      rentCar(request, response);
    } else if (request.getRequestURI().contains("/finish_add_car")) {
      addNewCar(request, response);
    }
  }

  private void deleteClient(HttpServletRequest request, HttpServletResponse response) {
    int clientIndex = Integer.parseInt(request.getParameter("id"));
    try {
      carService.makeAvailable(clientIndex);
      clientService.deleteById(clientIndex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    showClients(request, response);
  }

  private void openCarPage(HttpServletRequest request, HttpServletResponse response) {
    CarBean carBean = new CarBean();
    ownerService.setCurrentIndex(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("carBean", carBean);
    try {
      request.getRequestDispatcher("/new-car.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void openClientPage(HttpServletRequest request, HttpServletResponse response) {
    ClientBean clientBean = new ClientBean();
    carService.setCurrentIndex(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("clientBean", clientBean);
    try {
      request.getRequestDispatcher("/new-client.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void openOwnerPage(HttpServletRequest request, HttpServletResponse response) {
    OwnerBean ownerBean = new OwnerBean();
    request.setAttribute("ownerBean", ownerBean);
    try {
      request.getRequestDispatcher("/new-owner.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void showClients(HttpServletRequest request, HttpServletResponse response) {
    ClientBean clientBean = new ClientBean();
    try {
      List<Client> clientList = clientService.findAll();
      clientBean.setClients(clientList);
      request.setAttribute("clientBean", clientBean);
      request.getRequestDispatcher("/view-clients.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

  private void showAvailableCars(HttpServletRequest request, HttpServletResponse response,
      String mark) {
    CarBean carBean = new CarBean();
    try {
      List<Car> carList = carService.findAvailable(mark);
      carBean.setCars(carList);
      request.setAttribute("carBean", carBean);
      request.getRequestDispatcher("/view-cars-av.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void showAllCars(HttpServletRequest request, HttpServletResponse response, String mark) {
    CarBean carBean = new CarBean();
    try {
      List<Car> carList = carService.findAll(mark);
      carBean.setCars(carList);
      request.setAttribute("carBean", carBean);
      request.getRequestDispatcher("/view-cars-all.jsp").forward(request, response);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

      int clientIndex = (int) clientService.getIndex()
          .orElseThrow(() -> new IllegalArgumentException("invalid index"));
      int carIndex = carService.getCurrentIndex();

      carService.makeNotAvailable(carIndex, clientIndex);
    } catch (Exception e) {
      clientBean.setMessage("error input data");
    }
    showAvailableCars(request, response, "id");
  }

  private void addNewCar(HttpServletRequest request, HttpServletResponse response) {
    CarBean carBean = new CarBean();
    try {
      String model = request.getParameter("model");
      int price = Integer.parseInt(request.getParameter("price"));
      int ownerIndex = ownerService.getCurrentIndex();
      carService.saveToDB(model, price, ownerIndex);
    } catch (Exception e) {
      carBean.setMessage("Error input data for new Car!");
    }
    showOwners(request, response);
  }

  public void destroy() {
  }
}