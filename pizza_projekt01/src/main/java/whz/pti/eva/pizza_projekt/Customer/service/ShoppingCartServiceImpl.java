package whz.pti.eva.pizza_projekt.Customer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private PizzaRepository pizzaRepository;
    private ItemRepository itemRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public ShoppingCartServiceImpl(PizzaRepository pizzaRepository, ItemRepository itemRepository, ShoppingCartRepository shoppingCartRepository, CustomerRepository customerRepository) {
        this.pizzaRepository = pizzaRepository;
        this.itemRepository = itemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Item> getAllItems() {
        List<Item> itemList;
        return itemList = itemRepository.findAll();
    }

    @Transactional
    @Override
    public void addItemToCart(String loginName, String name, String description, int quantity) {
        Pizza pizza = new Pizza(name, description);
        pizzaRepository.save(pizza);
        Item item = new Item();
        item.addPizza(pizza).addQuantity(quantity);
        itemRepository.save(item);
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart shoppingCart;
        boolean newCart = true;
        List<ShoppingCart> allCart = shoppingCartRepository.findAll();
        for(ShoppingCart cart : allCart){
            if(cart.getCustomer().getLoginName().equals(loginName)){
                newCart = false;
                shoppingCart = cart;
                shoppingCart.addItems(item);
                break;
            }
        }
        if(newCart){
            shoppingCart = new ShoppingCart(new Date());
            shoppingCart.addItems(item);
            shoppingCart.addCustomer(customer);
            shoppingCartRepository.save(shoppingCart);
        }




    }


}
