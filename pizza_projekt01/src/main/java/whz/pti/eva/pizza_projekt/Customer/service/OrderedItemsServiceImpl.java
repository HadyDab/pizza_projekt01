package whz.pti.eva.pizza_projekt.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderedItemsServiceImpl implements OrderedItemsService{

    private  OrderedItemsRepository orderedItemsRepository;
    private CustomerRepository customerRepository;
    private ShoppingCartRepository shoppingCartRepository;

    /**
     *  A simple Construction Injection of Control
     * @param orderedItemsRepository
     * @param customerRepository
     * @param shoppingCartRepository
     */
    @Autowired
    public OrderedItemsServiceImpl(OrderedItemsRepository orderedItemsRepository, CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderedItemsRepository = orderedItemsRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    /**
     * Find all the order placed by this customer
     * @param loginName the login name of the customer
     * @return the list of all the order placed by the given customer
     */
    @Override
    public List<OrderedItems> findOrdersFromCustomer(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        return orderedItemsRepository.findOrderedItemsByCustomer(customer);
    }


    /**
     * Save an order placed by a customer
     * @param loginName the login name of the customer
     */
    @Transactional
    @Override
    public void saveOrder(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart = shoppingCartRepository.findShoppingCartByCustomer(customer);
        OrderedItems order = new OrderedItems();
        for(Item item : cart.getItemsList()){
            order.addordereditem(item);
        }
        cart.getItemsList().clear();
        shoppingCartRepository.save(cart);
        order.setOrderedDate(new Date());
        order.setCustomer(customer);
        customer.addOrderedItems(order);
        orderedItemsRepository.save(order);
        customerRepository.save(customer);

    }


    /**
     *  Find a particular order base on their id
     * @param id of the order to be found
     * @return order object
     */
    @Override
    public OrderedItems findbyID(long id) {
        return orderedItemsRepository.findById(id);
    }
}
