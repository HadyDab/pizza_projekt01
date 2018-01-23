package whz.pti.eva.pizza_projekt.Customer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.Customer.domain.PizzaRepository;
import whz.pti.eva.pizza_projekt.security.domain.PizzaCreateForm;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService{

    private PizzaRepository pizzaRepository;


    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    public List<Pizza> getAllPizzas() {

        return pizzaRepository.findAll();
    }

    @Override
    public void createPizza(String name, String description,double price) {
        Pizza pizza = new Pizza(name,description,price);
        pizzaRepository.save(pizza);
    }

    @Override
    public Pizza find(int id) {
        return pizzaRepository.findOne(id);
    }

    @Override
    public boolean existsByName(String name) {
        return pizzaRepository.existsByName(name);
    }

    @Override
    public Pizza creatPizza(PizzaCreateForm form) throws NumberFormatException {
        String amount = form.getPrice();
        double price = Double.parseDouble(amount);
        Pizza pizza = new Pizza();
        pizza.setName(form.getName());
        pizza.setDescription(form.getDescription());
        pizza.setPrice(price);
        pizzaRepository.save(pizza);
        return pizza;
    }

}


