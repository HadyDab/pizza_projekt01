package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.Customer.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addItemToCart(String loginName, Pizza pizza, int quantity);
    List<Item> getItemsinShoppingCart(String loginName);
    void createShoppingcart(String loginName);
    void updateQuantity(String loginName,int itemId, int quantity);
    void removethisItem(String loginName,int itemId);
    ShoppingCart findShoppingcartByCustomer(String loginName);

    void additemsfromorderHistory(String loginName,long orderhistoryid);

}
