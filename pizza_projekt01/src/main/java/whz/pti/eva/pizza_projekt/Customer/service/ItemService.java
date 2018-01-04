package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item findItem(int id);
}