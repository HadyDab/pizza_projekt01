package whz.pti.eva.pizza_projekt.Customer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Transactional
    @Override
    public void addItemToCart(String loginName, Pizza pizza, int quantity) {
        Customer customer = customerRepository.findByLoginName(loginName);
        Item item = new Item(pizza,quantity);
        itemRepository.save(item);
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByCustomer(customer);
        shoppingCart.addItems(item);
        shoppingCartRepository.save(shoppingCart);
    }


    @Override
    public List<Item> getItemsinShoppingCart(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        List<ShoppingCart> allCart = shoppingCartRepository.findAll();
        List<Item> allItemsinCart = new ArrayList<>();
        for(ShoppingCart cart : allCart){
            if(cart.getCustomer().getLoginName().equals(loginName)){
                allItemsinCart = cart.getItemsList();
            }
        }
        return allItemsinCart;
    }




    @Transactional
    @Override
    public void createShoppingcart(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);

        ShoppingCart shoppingCart = new ShoppingCart(new Date());

        shoppingCart.addCustomer(customer);
        customer.setShoppingCart(shoppingCart);

        customerRepository.save(customer);
        shoppingCartRepository.save(shoppingCart);


    }

    @Override
    public void updateQuantity(String loginName, int itemid, int quantity) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart =  shoppingCartRepository.findShoppingCartByCustomer(customer);
        Item item = cart.findthisItem(itemRepository.findOne(itemid));
        item.addQuantity(quantity);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removethisItem(String loginName, int itemId) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart =  shoppingCartRepository.findShoppingCartByCustomer(customer);
        cart.deleteItem(itemRepository.findOne(itemId));
        shoppingCartRepository.save(cart);
    }


}
