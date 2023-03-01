package fa.training.service;

import fa.training.dao.impl.CustomerDAOImpl;
import fa.training.dao.impl.OrderDAOImpl;
import fa.training.entities.Order;

import java.util.List;

public class OrderServiceImpl {

    public static CustomerDAOImpl cus = new CustomerDAOImpl();

    public static OrderDAOImpl ord = new OrderDAOImpl();

    public void displayAllOrdersByCustomerId() {
        Service.printfAllCustomer();
        System.out.println();
        int cusId = Validate.checkInputInt();
        if (cus.checkCustomerExit(cusId)) {
            List<Order> listOrd = ord.getAllOrdersByCustomerId(cusId);
            if (listOrd.size() != 0) {
                System.out.println("------------------------- List Order Of Customer ID: " + cusId + " -------------------------");
                for(Order order : listOrd){
                    System.out.println(order);
                }
            } else System.out.println("Customer don't have order.");
        } else System.out.println("Not found Customer ID!");
    }

    public static void displayTotalById() {
        int id = Validate.checkInputInt();
        if (checkId(id)) {
            if (ord.computeOrderTotal(id) != null) {
                System.out.println("Total of order " + id + " is " + ord.computeOrderTotal(id));
            } else System.out.println("Error!");
        } else {
            System.out.println("Not found Order ID!");
        }
    }

    public static boolean checkId(int orderId) {
        List<Order> listOrd = ord.getAllOrder();
        for (Order o : listOrd){
            if (o.getOrderId() == orderId) {
                return true;
            }
        }
        return false;
    }

    public static void displayAllOrder() {
        List<Order> listOrd = ord.getAllOrder();
        System.out.println("------------------------- List Order -------------------------");
        for(Order order : listOrd){
            System.out.println(order);
        }
    }

    public static void updateTotal() {
        displayAllOrder();
        System.out.println();
        int id;
        while (true){
            id = Validate.checkInputInt();
            if (checkId(id)){
                break;
            } else System.out.println("Not found Order ID!");
        }
        System.out.println("Old total: " + ord.getTotal(id));
        double newTotal = Validate.inputPositiveDouble("Enter new total: ");
        if(ord.setTotal(id, newTotal)){
            System.out.println("Update total success!");
        } else System.out.println("Update false!");
    }

    public static void addOrder() {
        System.out.println("--------------------------- Add A New Order ---------------------------");
        int cusId;
        int empId;
        Service.printfAllCustomer();
        System.out.println();
        while (true){
            cusId = Validate.checkInputInt();
            if(cus.checkCustomerExit(cusId)) break;
            else System.out.println("Not found Customer ID!");
        }
        Service.displayAllEmployee();
        System.out.println();
        while (true){
            empId = Validate.checkInputInt();
            if(Service.checkId(empId)) break;
            else System.out.println("Not found Employee ID!");
        }
        Order order = new Order(cusId, empId);
        if (ord.addOrder(order)){
            System.out.println("Add success!");
        } else {
            System.out.println("Add fault!");
        }
    }
}
