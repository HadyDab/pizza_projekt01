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


    /**
     *  A simple Construction Injection of Control
     * @param pizzaRepository
     */
    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * Get all the list of pizzas in the DB
     * @return list of all the pizzas in the DB
     */
    @Override
    public List<Pizza> getAllPizzas() {

        return pizzaRepository.findAll();
    }

    /**
     * Create a pizza and save it to the DB
     * @param name of the pizza to be save
     * @param description of the pizza
     * @param price of the pizza
     */
    @Override
    public void createPizza(String name, String description,double price) {
        Pizza pizza = new Pizza(name,description,price);
        pizzaRepository.save(pizza);
    }

    /**
     * Find a particular pizza base on the given id
     * @param id of the pizza to be found
     * @return pizza object
     */
    @Override
    public Pizza find(int id) {
        return pizzaRepository.findOne(id);
    }

    /**
     * Check if a pizza with the given name exit
     * @param name of the pizza to be checked
     * @return true if the pizza exit else return false
     */
    @Override
    public boolean existsByName(String name) {
        return pizzaRepository.existsByName(name);
    }

    /**
     * Create a pizza from the web app, and save it on the BD
     * @param form to be extract for pizza information
     * @return the pizza that was created
     * @throws NumberFormatException if a wrong number is entered. Price needs to be a double
     */
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


