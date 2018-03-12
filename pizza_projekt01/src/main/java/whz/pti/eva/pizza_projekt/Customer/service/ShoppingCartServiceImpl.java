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
    private OrderedItemsRepository orderedItemsRepository;

    /**
     *  A simple Construction Injection of Control
     * @param pizzaRepository
     * @param itemRepository
     * @param shoppingCartRepository
     * @param customerRepository
     * @param orderedItemsRepository
     */
    @Autowired
    public ShoppingCartServiceImpl(PizzaRepository pizzaRepository, ItemRepository itemRepository,
                                   ShoppingCartRepository shoppingCartRepository,
                                   CustomerRepository customerRepository,OrderedItemsRepository orderedItemsRepository) {
        this.pizzaRepository = pizzaRepository;
        this.itemRepository = itemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerRepository = customerRepository;
        this.orderedItemsRepository = orderedItemsRepository;
    }


    /**
     * Add this Pizza as Item to the shopppinf cart
     * @param loginName of the customer ordering
     * @param pizza the pizza to be added
     * @param quantity the quantity to be orderd
     */
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


    /**
     * Get all the items in the customer shoppingcart
     * @param loginName of the customer ordering
     * @return all the items in the customer shopping cart
     */
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


    /**
     * Create a shoppingcart for the given customer
     * @param loginName of the customer
     */
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

    /**
     * Update the quantity of items in the customer shoppingcart
     * @param loginName of the customer
     * @param itemid of which the quantity is to be updated
     * @param quantity the new quantity to be set
     */
    @Override
    public void updateQuantity(String loginName, int itemid, int quantity) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart =  shoppingCartRepository.findShoppingCartByCustomer(customer);
        Item item = cart.findthisItem(itemRepository.findOne(itemid));
        item.addQuantity(quantity);
        shoppingCartRepository.save(cart);
    }


    /**
     * Remove the item from the customer shoppingcart
     * @param loginName of the customer
     * @param itemId of the item to be removed
     */
    @Override
    public void removethisItem(String loginName, int itemId) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart =  shoppingCartRepository.findShoppingCartByCustomer(customer);
        cart.deleteItem(itemRepository.findOne(itemId));
        shoppingCartRepository.save(cart);
    }


    /**
     * Find a shoppingcart associated to the given customer
     * @param loginName of the customer
     * @return shoppingcart associated to the customer
     */
    @Override
    public ShoppingCart findShoppingcartByCustomer(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        return shoppingCartRepository.findShoppingCartByCustomer(customer);
    }


    /**
     * Reorder Items from order history
     * @param loginName of the customer ordering
     * @param orderhistoryid of the order history to reorder
     */
    @Transactional
    @Override
    public void additemsfromorderHistory(String loginName, long orderhistoryid) {

        Customer customer = customerRepository.findByLoginName(loginName);

        OrderedItems order = orderedItemsRepository.findById(orderhistoryid);

        ShoppingCart cart = shoppingCartRepository.findShoppingCartByCustomer(customer);

        order.getItemsordered().forEach(item -> cart.addItems(item));

        shoppingCartRepository.save(cart);

    }


}
