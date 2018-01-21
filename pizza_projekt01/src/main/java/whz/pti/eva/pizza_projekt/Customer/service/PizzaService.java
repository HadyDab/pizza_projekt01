package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.security.domain.PizzaCreateForm;

import java.util.List;

public interface PizzaService {

    List<Pizza> getAllPizzas();
    void createPizza(String name,String description,double price);
    Pizza find(int id);
    boolean existsByName(String name);
    Pizza creatPizza(PizzaCreateForm form);
}
