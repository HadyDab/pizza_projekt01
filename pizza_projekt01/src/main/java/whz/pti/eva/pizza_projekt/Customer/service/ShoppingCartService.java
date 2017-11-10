package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;

import java.util.Date;
import java.util.List;

public interface ShoppingCartService {

    List<Item> getAllItems();
    void CreateShoppingCart(Date creationDate, Customer customer);
    void addItemToCart(Item item , String name, String Description);
}
