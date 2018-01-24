package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.OrderedItems;

import java.util.List;

public interface OrderedItemsService {

    List<OrderedItems> findOrdersFromCustomer(String loginName);
    void saveOrder(String loginName);
    OrderedItems findbyID(long id);
}
