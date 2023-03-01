package fa.training.service.LineItem;

import fa.training.dao.impl.*;
import fa.training.entities.Customer;
import fa.training.entities.LineItem;
import fa.training.entities.Order;
import fa.training.service.Service;
import fa.training.service.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;


public class LineItemServiceDAO {

    public static Scanner sc = new Scanner(System.in);

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static CustomerDAOImpl cus = new CustomerDAOImpl();
    public static List<Customer> customerList;

    public static OrderDAOImpl ord = new OrderDAOImpl();
    public static List<Order> orderList;

    public static LineItemDAOImpl lid = new LineItemDAOImpl();
    public static List<LineItem> lineItemList;

    public static EmployeeDAOImpl empl = new EmployeeDAOImpl();

    public void displayAllItemsByOrderId() {
        Service.printfAllOrders();
        System.out.println();
        int id = Validate.checkInputInt();
        if (Service.lid.checkOrderIDExit(id)) {
            List<LineItem> listItem = lid.getAllItemsByOrderId(id);
            if (listItem.size() != 0) {
                System.out.println("------------------------- List LineItems Of Order ID: " + id + " -------------------------");
                System.out.printf("%-15s %-15s %-15s %-15s", "Order_ID", "Product_ID", "Quantity", "Price");
                listItem.forEach(lineItem -> System.out.print(lineItem.toString()));
            } else System.out.println("Order don't have items.");
        } else System.out.println("Not found Order ID!");
    }

    public static void addLineItem() throws IOException {
        System.out.println("----------------------- Add A New Item -----------------------");
        int ordId;
        int proId;
        Service.printfAllOrders();
        System.out.println();
        while (true){
            ordId = Validate.checkInputInt();
            if(Service.lid.checkOrderIDExit(ordId)) break;
            else System.out.println("Not found Order ID!");
        }
        Service.displayAllProduct();
        System.out.println();
        while (true){
            proId = Validate.checkInputInt();
            if(Service.checkProductId(proId)) break;
            else System.out.println("Not found Product ID!");
        }
        if (checkLineItem(ordId, proId)) {
            List<LineItem> listItem = lid.getAllItemsByOrderId(ordId);
            for(LineItem i : listItem){
                if (i.getProductId() == proId){
                    String str = Validate.inputStringRegex("The product has been purchased " + i.getQuantity() + " pcs. Would you like to purchase more? [Y/N]", "[YNyn]");
                    if (str.equalsIgnoreCase("Y")){
                        int moreQuantity = Validate.inputPositiveInt("Enter extra quantity: ");
                        double price = ProductDAOImpl.getListPrice(proId);
                        int quantity = moreQuantity + i.getQuantity();
                        if(lid.updateItem(ordId, proId, quantity, price * quantity)) {
                            ord.setTotal(ordId, ord.getTotal(ordId) + price * moreQuantity);
                            System.out.println("Add extra quantity success!");
                        }
                        else System.out.println("Add extra quantity fault!");
                    } else break;
                }
            }
        } else {
            int quantity = Validate.checkInputInt();
            LineItem lineItem = new LineItem(ordId, proId, quantity);
            if (lid.addLineItem(lineItem)){
                System.out.println("Add success!");
            } else {
                System.out.println("Add fault!");
            }
        }
    }

    public static boolean checkLineItem(int orderId, int productId) {
        List<LineItem> listItem = lid.getAllItemsByOrderId(orderId);
        for (LineItem i : listItem){
            if (i.getProductId() == productId) {
                return true;
            }
        }
        return false;
    }

}
