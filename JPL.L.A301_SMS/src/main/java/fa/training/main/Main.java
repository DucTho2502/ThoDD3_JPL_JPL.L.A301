package fa.training.main;

import fa.training.service.LineItem.LineItemServiceDAO;
import fa.training.service.OrderServiceImpl;
import fa.training.service.Service;
import fa.training.service.Validate;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("\n========= Sale Management  =========\n");
            System.out.println("1. List all customers");
            System.out.println("2. List all orders by customer id");
            System.out.println("3. List all line items for an order");
            System.out.println("4. Compute order total of a order");
            System.out.println("5. Add a customer");
            System.out.println("6. Delete a customer");
            System.out.println("7. Update a customer");
            System.out.println("8. Create an order");
            System.out.println("9. Create a line item");
            System.out.println("10.Update an order total");
            System.out.println("11. Exit");
            int choice = Validate.checkInputIntLimit(1, 11);
            switch (choice) {
                case 1:
                    Service.printfAllCustomer();
                    break;
                case 2:
                    Service.printfAllOrders();
                    break;
                case 3:
                    Service.printfAllLineItemByOrderID();
                    break;
                case 4:
                    OrderServiceImpl.displayTotalById();
                    break;
                case 5:
                    Service.addCustomer();
                    break;
                case 6:
                    Service.deleteCustomer();
                    break;
                case 7:
                    Service.updateCustomer();
                    break;
                case 8:
                    OrderServiceImpl.addOrder();
                    break;
                case 9:
                    LineItemServiceDAO.addLineItem();
                    break;
                case 10:
                    OrderServiceImpl.updateTotal();
                    break;
                case 11:
                    System.out.println("Bye!!!!!!!!!");
                    System.exit(0);
            }
        }
    }

}
