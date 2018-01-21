package whz.pti.eva.pizza_projekt.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderedItemsServiceImpl implements OrderedItemsService{

    OrderedItemsRepository orderedItemsRepository;
    CustomerRepository customerRepository;
    ShoppingCartRepository shoppingCartRepository;


    @Autowired
    public OrderedItemsServiceImpl(OrderedItemsRepository orderedItemsRepository, CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderedItemsRepository = orderedItemsRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<OrderedItems> findOrdersFromCustomer(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        return orderedItemsRepository.findOrderedItemsByCustomer(customer);
    }

    @Transactional
    @Override
    public void saveOrder(String loginName) {
        Customer customer = customerRepository.findByLoginName(loginName);
        ShoppingCart cart = shoppingCartRepository.findShoppingCartByCustomer(customer);
        OrderedItems order = new OrderedItems();
        List<Item> ordereditems = cart.getItemsList();
        order.setItemsordered(ordereditems);
        order.setCustomer(customer);
        orderedItemsRepository.save(order);
        customer.addOrderedItems(order);
        customerRepository.save(customer);


    }
}
