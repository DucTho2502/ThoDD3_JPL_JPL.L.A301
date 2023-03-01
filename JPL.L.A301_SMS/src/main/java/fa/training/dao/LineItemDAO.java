package fa.training.dao;

import fa.training.entities.LineItem;

import java.util.List;

public interface LineItemDAO {

    public List<LineItem> getAllItemsByOrderId(int orderId);

    public boolean addLineItem(LineItem item);

    public boolean updateItem(int orderId, int productId, int quantity, double price);

}
