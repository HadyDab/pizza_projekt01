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

    @Override
    public OrderedItems findbyID(long id) {
        return orderedItemsRepository.findById(id);
    }
}
