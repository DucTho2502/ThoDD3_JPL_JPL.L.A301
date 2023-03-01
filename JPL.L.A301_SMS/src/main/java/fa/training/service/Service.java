package fa.training.service;


import fa.training.dao.CustomerDAO;
import fa.training.dao.LineItemDAO;
import fa.training.dao.OrderDAO;
import fa.training.dao.impl.*;
import fa.training.entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Service {

    public static Scanner sc = new Scanner(System.in);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static CustomerDAOImpl cus = new CustomerDAOImpl();
    public static List<Customer> customerList;

    public static OrderDAOImpl ord = new OrderDAOImpl();
    public static List<Order> orderList;

    public static LineItemDAOImpl lid = new LineItemDAOImpl();
    public static List<LineItem> lineItemList;
    
    public static EmployeeDAOImpl empl = new EmployeeDAOImpl();

    public static ProductDAOImpl prol = new ProductDAOImpl();



    public static void printfAllCustomer(){
        System.out.println("-----------List Of Customers------------");
        customerList = cus.getAllCustomer();
        for(Customer cu : customerList){
            System.out.println(cu);
        }
    }

    public static void printfAllOrders(){
        System.out.print("Enter CustomerID: ");
        int cusID = Validate.checkInputInt();
        if(cus.checkCustomerExit(cusID)==true){
            System.out.println("------------List Of Orders-------------");
            orderList = ord.getAllOrdersByCustomerId(cusID);
            for(Order ord : orderList){
                System.out.println(ord);
            }
        } else {
            System.out.println("Customer doesn't exits.");
            return;
        }
    }

    public static void printfAllLineItemByOrderID(){
        System.out.print("Enter OrderID: ");
        int ordID = Validate.checkInputInt();
        if(lid.checkOrderIDExit(ordID)==true){
            System.out.println("-------------List Of LineItem-------------");
            lineItemList = lid.getAllItemsByOrderId(ordID);
            for(LineItem lineItem : lineItemList){
                System.out.println(lineItem);
            }
        } else {
            System.out.println("OrderID doesn't exits.");
            return;
        }
    }

    public static void addCustomer() throws IOException {
        System.out.println("---------------------- Add A New Customer ----------------------");
        String name = Validate.inputString("Enter Customer Name: ");
        Customer customer = new Customer(name);
        if (cus.addCustomer(customer)){
            System.out.println("Add success!");
        } else {
            System.out.println("Add fault!");
        }
    }

    public static void deleteCustomer() {
        printfAllCustomer();
        System.out.println();
        System.out.println("---------------------- Delete A Customer ----------------------");
        int id = Validate.checkInputInt();
        if (cus.checkCustomerExit(id)) {
            if (cus.deleteCustomer(id)){
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete fault!");
            }
        } else System.out.println("Not found Customer ID!");
    }

    public static void updateCustomer() throws IOException {
        System.out.println("---------------------- Update A Customer ----------------------");
        int id = Validate.checkInputInt();
        Customer customer = cus.getCustomer(id);
        String name = Validate.inputString("Enter new Customer Name: ");
        customer.setCustomerName(name);
        if (cus.checkCustomerExit(id)) {
            if(cus.updateCustomer(customer)){
                System.out.println("Update success!");
            } else {
                System.out.println("Update fault!");
            }
        } else System.out.println("Not found Customer ID!");
    }

    public static void displayAllEmployee() {
        List<Employee> listEmp = empl.getAllEmployee();
        System.out.println("---------------------------- List Employee ----------------------------");
        for(Employee employee : listEmp){
            System.out.println(employee);
        }
    }

    public static boolean checkId(int employeeId) {
        List<Employee> listEmp = empl.getAllEmployee();
        for (Employee e : listEmp){
            if (e.getEmployeeId() == employeeId) {
                return true;
            }
        }
        return false;
    }

    public static void displayAllProduct() {
        List<Product> listPro = prol.getAllProduct();
        System.out.println("------------------------- List Product -------------------------");
        for(Product product : listPro){
            System.out.println(product);
        }
    }

    public static boolean checkProductId(int productId) {
        List<Product> listPro = prol.getAllProduct();
        for (Product p : listPro){
            if (p.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }
}
