package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.ShoppingCart;

import java.util.Date;
import java.util.List;

public interface ShoppingCartService {

    List<Item> getAllItems();

    void addItemToCart(String loginName, String name, String description, int quantity);

}
